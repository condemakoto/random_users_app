package com.kun.randomusers.domain.interactor;
import com.kun.randomusers.data.ServiceFacade;
import com.kun.randomusers.data.callback.BaseCallback;
import com.kun.randomusers.data.entity.UserPageEntity;
import com.kun.randomusers.domain.callback.UsersCallback;
import com.kun.randomusers.domain.mapper.UserMapper;

import javax.inject.Inject;


/**
 * @author Julio Kun
 * @version 0.1
 */

public class UserInteractor {

    private ServiceFacade serviceFacade;
    private UserMapper mapper;

    @Inject
    public UserInteractor(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
        this.mapper = new UserMapper();
    }

    public void getUsers(final UsersCallback usersCallback) {
        serviceFacade.getUsers(1, null, new BaseCallback<UserPageEntity>() {
            @Override
            public void onError() {
                usersCallback.onError();
            }

            @Override
            public void onDataAvailable(UserPageEntity data) {
                usersCallback.onUsersAvailable(mapper.map(data));
            }
        });
    }

    public void getMoreUsers(int page, String seed, final UsersCallback usersCallback) {
        page++;
        serviceFacade.getUsers(page, seed, new BaseCallback<UserPageEntity>() {
            @Override
            public void onError() {
                usersCallback.onError();
            }

            @Override
            public void onDataAvailable(UserPageEntity data) {
                usersCallback.onUsersAvailable(mapper.map(data));
            }
        });
    }
}
