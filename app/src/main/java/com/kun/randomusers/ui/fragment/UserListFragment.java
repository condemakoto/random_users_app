package com.kun.randomusers.ui.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kun.randomusers.R;

import com.kun.randomusers.di.component.DaggerViewComponent;
import com.kun.randomusers.domain.model.User;
import com.kun.randomusers.domain.model.UsersListPage;
import com.kun.randomusers.presenter.UserPresenter;
import com.kun.randomusers.presenter.view.UsersListView;
import com.kun.randomusers.ui.adapter.UsersAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Julio Kun
 * @version 0.1
 */

public class UserListFragment extends BaseFragment implements UsersListView, UsersAdapter.OnUserSelectedListener {

    @Inject
    protected UserPresenter mPresenter;

    @Bind(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerView)
    protected RecyclerView recyclerView;
    @Bind(R.id.emptyDataTV)
    protected TextView emptyUsersTV;
    private UsersAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private UsersListPage users;
    private final static String KEY_USERS = "users";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        DaggerViewComponent.builder().build().inject(this);
        mPresenter.addView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, parent, false);
        ButterKnife.bind(this, view);
        mAdapter = new UsersAdapter(getContext(), this);
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.setOnRefreshListener(this.onRefreshListener);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);

        if (Build.VERSION.SDK_INT < 23) {
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    checkRefreshCondition();
                }
            });
        } else {
            recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    checkRefreshCondition();
                }
            });
        }

        if (savedInstanceState != null) {
            mPresenter.setPreviousState((UsersListPage) savedInstanceState.getParcelable(KEY_USERS));
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (users != null) {
            outState.putParcelable(KEY_USERS, users);
        }
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void clearUsers() {
        mAdapter.clearUsers();
    }

    @Override
    public void showUsers(UsersListPage users) {
        this.users = users;
        mAdapter.addUsers(users.getUsers());
        recyclerView.setVisibility(View.VISIBLE);
        emptyUsersTV.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Snackbar.make(getView(), getString(R.string.errorUserRequest), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyScreen() {
        recyclerView.setVisibility(View.GONE);
        emptyUsersTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUserSelected(User user) {
        ((ActivityCallback) getActivity()).showUserDetail(user);
    }

    public interface ActivityCallback {
        void showUserDetail(User user);
    }

    private void checkRefreshCondition() {
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = mLayoutManager.getItemCount();
        int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

        mPresenter.checkRefresh(visibleItemCount, totalItemCount, firstVisibleItem);
    }

    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.onRefresh();
        }
    };
}
