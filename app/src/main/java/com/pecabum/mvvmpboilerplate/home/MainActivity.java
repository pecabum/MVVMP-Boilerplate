package com.pecabum.mvvmpboilerplate.home;

import com.bluelinelabs.conductor.Controller;
import com.pecabum.mvvmpboilerplate.R;
import com.pecabum.mvvmpboilerplate.base.BaseActivity;
import com.pecabum.mvvmpboilerplate.trending.TrendingReposController;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        return new TrendingReposController();
    }
}
