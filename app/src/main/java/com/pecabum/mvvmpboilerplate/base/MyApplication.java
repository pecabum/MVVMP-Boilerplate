package com.pecabum.mvvmpboilerplate.base;

import android.app.Application;

import com.pecabum.mvvmpboilerplate.di.ActivityInjector;

import javax.inject.Inject;

/**
 * Created by ppetrov on 21.02.18.
 */

public class MyApplication extends Application {

    @Inject ActivityInjector activityInjector;

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
