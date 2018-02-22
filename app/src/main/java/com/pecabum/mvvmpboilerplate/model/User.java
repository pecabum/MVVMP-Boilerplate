package com.pecabum.mvvmpboilerplate.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by ppetrov on 22.02.18.
 */

@AutoValue
public abstract class User {

    public abstract long id();

    public abstract String login();

    public static JsonAdapter<User> jsonAdapter(Moshi moshi) {
        return new AutoValue_User.MoshiJsonAdapter(moshi);
    }
}
