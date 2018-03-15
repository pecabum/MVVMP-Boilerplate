package com.pecabum.mvvmpboilerplate.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

/**
 * Created by ppetrov on 21.02.18.
 */

public interface ScreenNavigator {

    /**
     * Initializing the navigator
     * Keep in mind that @{@link Router} may have no root controller
     * */
    void initWithRouter(Router router, Controller rootScreen);

    /**
     * Popping the current controller and return value of whether we can pop or not
     * */
    boolean pop();

    void goToRepoDetails(String owner, String name);

    /**
     * Clearing the @{@link Router} value
     * */
    void clear();

}
