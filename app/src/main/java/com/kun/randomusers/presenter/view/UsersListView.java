package com.kun.randomusers.presenter.view;

import com.kun.randomusers.domain.model.UsersListPage;

/**
 * @author Julio Kun
 * @version 0.1
 */

public interface UsersListView extends BaseView {
    void clearUsers();
    void showUsers(UsersListPage users);
    void showEmptyScreen();
    void showError();
}
