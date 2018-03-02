package com.pecabum.mvvmpboilerplate.home;

import com.bluelinelabs.conductor.Controller;
import com.pecabum.mvvmpboilerplate.di.ControllerKey;
import com.pecabum.mvvmpboilerplate.trending.TrendingReposComponent;
import com.pecabum.mvvmpboilerplate.trending.TrendingReposController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        TrendingReposComponent.class,
})
public abstract class TestScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder builder);
}
