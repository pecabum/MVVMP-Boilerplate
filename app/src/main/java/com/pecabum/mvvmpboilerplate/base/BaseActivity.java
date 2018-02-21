package com.pecabum.mvvmpboilerplate.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.pecabum.mvvmpboilerplate.R;
import com.pecabum.mvvmpboilerplate.di.Injector;
import com.pecabum.mvvmpboilerplate.di.ScreenInjector;
import com.pecabum.mvvmpboilerplate.ui.ScreenNavigator;

import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by ppetrov on 21.02.18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static String INSTANCE_ID_KEY = "instance_id";

    @Inject
    ScreenInjector screenInjector;

    @Inject
    ScreenNavigator screenNavigator;

    private Router router;

    private String instanceId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        } else {
            instanceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);
        setContentView(layoutRes());

        ViewGroup screenContainer = findViewById(R.id.screen_container);
        if (screenContainer == null) {
            throw new NullPointerException("Activity must have a view with id: screen_container");
        }

        router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
        screenNavigator.initWithRouter(router, initialScreen());
        monitorBackStack();
        super.onCreate(savedInstanceState);
    }

    /**
     * Adding listener to the Conductor's router in order to monitor
     * screens changing and clear their injectors
     */
    private void monitorBackStack() {
        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(@Nullable Controller to,
                                        @Nullable Controller from,
                                        boolean isPush,
                                        @NonNull ViewGroup container,
                                        @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(@Nullable Controller to,
                                          @Nullable Controller from,
                                          boolean isPush,
                                          @NonNull ViewGroup container,
                                          @NonNull ControllerChangeHandler handler) {
                if (!isPush && from != null) {
                    Injector.clearComponent(from);
                }
            }
        });
    }


    /**
     * Providing the layout from the child activities
     */
    @LayoutRes
    protected abstract int layoutRes();

    /**
     * We're keeping the method of providing controller abstract in order to allow using multiple activities
     */
    protected abstract Controller initialScreen();

    /**
     * If the screen navigator did not pop then we want to finish the activity
     * */
    @Override
    public void onBackPressed() {
        if (!screenNavigator.pop()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY, instanceId);
    }

    public String getInstanceId() {
        return instanceId;
    }


    /**
     * Removing
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        screenNavigator.clear();
        if (isFinishing()) {
            Injector.clearComponent(this);
        }
    }

    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }
}
