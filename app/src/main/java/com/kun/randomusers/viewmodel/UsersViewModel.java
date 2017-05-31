package com.kun.randomusers.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.kun.randomusers.domain.interactor.UserInteractor;
import com.kun.randomusers.domain.model.User;
import com.kun.randomusers.domain.model.UsersListPage;

import rx.Observable;

/**
 * @author Julio
 * @version 1.0
 * <p>
 *
 * </p>
 */

public class UsersViewModel extends ViewModel {

    private UsersListPage usersPage;
    private UserInteractor userInteractor;
    private User selectedUser;


    public Observable<UsersListPage> getUsers() {
        if (usersPage == null) {
            return Observable.just(usersPage);
        } else {
            return userInteractor.getUsers(null);
        }
    }

    public Observable<UsersListPage> getMoreUsers() {
        if (usersPage != null) {
            return userInteractor.getMoreUsers(usersPage.getPage(), usersPage.getSeed(), null);
        }
        return Observable.empty();
    }

    public void setSelectedUser(User user) {
        this.selectedUser = user;
    }

    public void clearSelectedUser() {
        this.selectedUser = null;
    }
}
