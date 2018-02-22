package com.pecabum.mvvmpboilerplate.base;

import com.pecabum.mvvmpboilerplate.data.RepoServiceModule;
import com.pecabum.mvvmpboilerplate.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ppetrov on 21.02.18.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        RepoServiceModule.class,
})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);
}
