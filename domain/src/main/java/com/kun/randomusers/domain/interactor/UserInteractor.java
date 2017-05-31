package com.kun.randomusers.domain.interactor;
import com.kun.randomusers.data.ServiceFacade;
import com.kun.randomusers.data.callback.BaseCallback;
import com.kun.randomusers.data.entity.UserPageEntity;
import com.kun.randomusers.domain.callback.UsersCallback;
import com.kun.randomusers.domain.mapper.UserMapper;
import com.kun.randomusers.domain.model.User;
import com.kun.randomusers.domain.model.UsersListPage;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;


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

    public Observable<UsersListPage> getUsers(final UsersCallback usersCallback) {
        return serviceFacade.getUsers(1, null).map(new Func1<UserPageEntity, UsersListPage>() {
            @Override
            public UsersListPage call(UserPageEntity userPageEntity) {
                return mapper.map(userPageEntity);
            }
        });
    }

    public Observable<UsersListPage> getMoreUsers(int page, String seed, final UsersCallback usersCallback) {
        page++;
        return serviceFacade.getUsers(page, seed).map(new Func1<UserPageEntity, UsersListPage>() {
            @Override
            public UsersListPage call(UserPageEntity userPageEntity) {
                return mapper.map(userPageEntity);
            }
        });
    }

}
