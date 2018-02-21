package com.pecabum.mvvmpboilerplate.ui;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ppetrov on 21.02.18.
 */
@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);
}
