package com.pecabum.mvvmpboilerplate.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.pecabum.mvvmpboilerplate.di.Injector;

/**
 * Created by ppetrov on 21.02.18.
 */

public class BaseController extends Controller {

    private boolean injected = false;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return null;
    }

    @Override
    protected void onContextAvailable(@NonNull Context context) {

        // Controller instances are retained across config changes so this method can be called more than once
        // This makes sure we don't waste any time  injecting more than once though technically it wouldn't
        // change functionality
        if (!injected) {
            Injector.inject(this);
            injected = true;
        }

        super.onContextAvailable(context);
    }
}
