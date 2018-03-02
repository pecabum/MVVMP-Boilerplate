package com.pecabum.mvvmpboilerplate.home;

import com.pecabum.mvvmpboilerplate.di.ActivityScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        TestScreenBindingModule.class,
})
public interface TestMainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }
}
