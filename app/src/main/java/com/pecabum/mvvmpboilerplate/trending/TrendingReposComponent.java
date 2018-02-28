package com.pecabum.mvvmpboilerplate.trending;

import com.pecabum.mvvmpboilerplate.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by ppetrov on 21.02.18.
 */
@ScreenScope
@Subcomponent
public interface TrendingReposComponent extends AndroidInjector<TrendingReposController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TrendingReposController> {
    }

}
