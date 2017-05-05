package com.kun.randomusers.domain.callback;

import com.kun.randomusers.domain.model.UsersListPage;

/**
 * @author Julio Kun
 * @version 0.1
 */

public interface UsersCallback {
    void onUsersAvailable(UsersListPage usersList);
    void onError();
}
