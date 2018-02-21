package com.pecabum.mvvmpboilerplate.home;

import com.bluelinelabs.conductor.Controller;
import com.pecabum.mvvmpboilerplate.di.ControllerKey;
import com.pecabum.mvvmpboilerplate.trending.TrendingReposController;
import com.pecabum.mvvmpboilerplate.trending.TrendingRepostComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by ppetrov on 21.02.18.
 */

@Module(subcomponents = {
        TrendingRepostComponent.class
})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingRepostComponent.Builder builder);

}
