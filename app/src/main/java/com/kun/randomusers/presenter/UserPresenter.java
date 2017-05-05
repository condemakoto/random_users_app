package com.kun.randomusers.presenter;

import com.kun.randomusers.domain.callback.UsersCallback;
import com.kun.randomusers.domain.interactor.UserInteractor;
import com.kun.randomusers.domain.model.User;
import com.kun.randomusers.domain.model.UsersListPage;
import com.kun.randomusers.presenter.view.UsersListView;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author Julio Kun
 * @version 0.1
 */

public class UserPresenter extends BasePresenter<UsersListView> {

    private UserInteractor userInteractor;
    private UsersListPage userListPage;
    private boolean loading;
    private final int VISIBLE_THRESHOLD = 2;

    @Inject
    public UserPresenter(UserInteractor userInteractor) {
        this.userInteractor = userInteractor;
    }

    @Override
    public void onStart() {
        if (userListPage == null) {
            onRefresh();
        }
    }

    public void setPreviousState(UsersListPage userListPage) {
        this.userListPage = userListPage;
        if (userListPage != null) {
            getView().showUsers(userListPage);
        }
    }

    public void checkRefresh(int visibleItemCount, int totalItemCount, int firstVisibleItem) {
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            loading = true;

            getView().showProgress();

            userInteractor.getMoreUsers(userListPage.getPage(), userListPage.getSeed(), new UsersCallback() {

                @Override
                public void onUsersAvailable(UsersListPage usersList) {

                    ArrayList<User> users = userListPage.getUsers();
                    users.addAll(usersList.getUsers());
                    usersList.setUsers(users);

                    userListPage = usersList;

                    loading = false;
                    UsersListView view = getView();
                    if (view != null) {
                        view.hideProgress();
                        view.showUsers(userListPage);
                    }
                }

                @Override
                public void onError() {
                    loading = false;
                    UsersListView view = getView();
                    if (view != null) {
                        view.hideProgress();
                        view.showError();
                    }
                }
            });
        }
    }

    public void onRefresh() {
        getView().showProgress();

        userInteractor.getUsers(new UsersCallback() {

            @Override
            public void onUsersAvailable(UsersListPage usersList) {
                userListPage = usersList;
                UsersListView view = getView();
                if (view != null) {
                    view.clearUsers();
                    view.hideProgress();
                    view.showUsers(userListPage);
                }
            }

            @Override
            public void onError() {
                UsersListView view = getView();
                if (view != null) {
                    view.hideProgress();
                    view.showEmptyScreen();
                    view.showError();
                }
            }
        });
    }
}
