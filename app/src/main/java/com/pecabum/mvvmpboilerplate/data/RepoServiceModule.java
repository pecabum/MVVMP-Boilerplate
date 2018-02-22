package com.pecabum.mvvmpboilerplate.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by pecabum on 22.02.18.
 */
@Module
public abstract class RepoServiceModule {

    @Provides
    @Singleton
    static RepoService provideRepoService(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }

}
