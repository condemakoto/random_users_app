package com.kun.randomusers.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kun.randomusers.R;
import com.kun.randomusers.domain.model.User;
import com.kun.randomusers.ui.adapter.UserDetailAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Julio Kun
 * @version 0.1
 *
 */

public class UserDetailFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    protected RecyclerView recyclerView;
    private UserDetailAdapter mAdapter;
    private User user;

    private final static String KEY_USER = "user";

    public static UserDetailFragment newInstance(User user) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail, parent, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (user == null) {
            mAdapter = new UserDetailAdapter(true, (User) getArguments().getParcelable(KEY_USER));
        } else {
            mAdapter = new UserDetailAdapter(true, user);
        }
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    public void loadUser(User user) {
        this.user = user;
        mAdapter.setUser(user);
    }
}
