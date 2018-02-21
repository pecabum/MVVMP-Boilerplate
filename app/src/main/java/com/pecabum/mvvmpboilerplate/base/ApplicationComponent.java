package com.pecabum.mvvmpboilerplate.base;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ppetrov on 21.02.18.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class
})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);
}
