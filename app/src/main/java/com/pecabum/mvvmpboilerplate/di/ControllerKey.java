package com.pecabum.mvvmpboilerplate.di;

import com.bluelinelabs.conductor.Controller;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Created by ppetrov on 21.02.18.
 */
@MapKey
@Target(ElementType.METHOD)
public @interface ControllerKey {

    Class<? extends Controller> value();
}
