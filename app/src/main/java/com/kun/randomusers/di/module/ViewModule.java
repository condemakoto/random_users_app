package com.kun.randomusers.di.module;

import com.kun.randomusers.data.ServiceFacade;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Julio Kun
 * @version 0.1
 */
@Module
public class ViewModule {


    @Provides
    @Singleton
    ServiceFacade provideServiceFacade() {
        return new ServiceFacade();
    }

}
