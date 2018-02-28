package com.pecabum.mvvmpboilerplate.home;

import com.pecabum.mvvmpboilerplate.di.ActivityScope;
import com.pecabum.mvvmpboilerplate.ui.NavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by ppetrov on 21.02.18.
 */

@ActivityScope
@Subcomponent(
        modules = {
                MainScreenBindingModule.class,
                NavigationModule.class,
        }
)
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
        @Override
            public void seedInstance(MainActivity instance) { /* Preventing injecting activities */}
    }

}
