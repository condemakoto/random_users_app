package com.kun.randomusers.di.component;

import com.kun.randomusers.di.PerActivity;
import com.kun.randomusers.di.module.ViewModule;
import com.kun.randomusers.ui.fragment.UserDetailFragment;
import com.kun.randomusers.ui.fragment.UserListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Julio Kun
 * @version 0.1
 */
@PerActivity
@Singleton
@Component(modules = ViewModule.class)
public interface ViewComponent {
    void inject(UserListFragment fragment);
}
