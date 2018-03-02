package com.pecabum.mvvmpboilerplate.base;

import android.app.Application;

import com.pecabum.mvvmpboilerplate.BuildConfig;
import com.pecabum.mvvmpboilerplate.di.ActivityInjector;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by ppetrov on 21.02.18.
 */

public class MyApplication extends Application {

    @Inject ActivityInjector activityInjector;

    protected ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = initComponent();
        component.inject(this);

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    protected ApplicationComponent initComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
