package com.pecabum.mvvmpboilerplate.details;

import com.pecabum.mvvmpboilerplate.di.ScreenScope;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by pecabum on 07.03.18.
 */

@ScreenScope
@Subcomponent
public interface RepoDetailsComponent extends AndroidInjector<RepoDetailsController>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoDetailsController> {
        @BindsInstance
        public abstract void bindRepoOwner(@Named("repo_owner") String repoOwner);

        @BindsInstance
        public abstract void bindRepoName(@Named("repo_name") String repoName);

        @Override
        public void seedInstance(RepoDetailsController instance) {
           bindRepoName(instance.getArgs().getString(RepoDetailsController.KEY_REPO_NAME));
           bindRepoOwner(instance.getArgs().getString(RepoDetailsController.KEY_REPO_OWNER));
        }
    }
}
