package com.pecabum.mvvmpboilerplate.base;

import android.app.Activity;

import com.pecabum.mvvmpboilerplate.home.MainActivity;
import com.pecabum.mvvmpboilerplate.home.MainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by ppetrov on 21.02.18.
 */

@Module(subcomponents = {
        MainActivityComponent.class
})
public abstract class ActivityBindingModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivityInjector(MainActivityComponent.Builder builder);
}
