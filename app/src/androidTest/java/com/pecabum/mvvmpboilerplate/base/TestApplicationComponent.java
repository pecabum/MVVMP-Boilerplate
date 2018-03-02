package com.pecabum.mvvmpboilerplate.base;

import com.pecabum.mvvmpboilerplate.data.TestRepoServiceModule;
import com.pecabum.mvvmpboilerplate.networking.ServiceModule;
import com.pecabum.mvvmpboilerplate.trending.TrendingReposControllerTest;
import com.pecabum.mvvmpboilerplate.ui.NavigationModule;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        NavigationModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(TrendingReposControllerTest trendingReposControllerTest);
}
