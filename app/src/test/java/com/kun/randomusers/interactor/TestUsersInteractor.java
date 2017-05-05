package com.kun.randomusers.interactor;

import com.kun.randomusers.data.ServiceFacade;
import com.kun.randomusers.domain.callback.UsersCallback;
import com.kun.randomusers.domain.interactor.UserInteractor;
import com.kun.randomusers.domain.model.User;
import com.kun.randomusers.domain.model.UsersListPage;

import java.util.ArrayList;

/**
 * @author Julio Kun
 * @version 0.1
 */

public class TestUsersInteractor extends UserInteractor {

    private UsersCallback callback;

    public TestUsersInteractor(ServiceFacade serviceFacade) {
        super(serviceFacade);
    }

    @Override
    public void getUsers(UsersCallback usersCallback) {
        this.callback = usersCallback;
    }

    @Override
    public void getMoreUsers(int page, String seed, UsersCallback usersCallback) {
        this.callback = usersCallback;
    }

    public void responseWithEmptyList() {
        UsersListPage page = new UsersListPage();
        page.setSeed("");
        page.setPage(1);
        page.setUsers(new ArrayList<User>());

        callback.onUsersAvailable(page);
    }

    public void responseWithNullList() {
        callback.onUsersAvailable(null);
    }

    public void responseWithOneUser() {
        UsersListPage page = new UsersListPage();
        page.setSeed("");
        page.setPage(1);

        ArrayList<User> users = new ArrayList<>();
        users.add(new User());

        page.setUsers(users);

        callback.onUsersAvailable(page);
    }
}
