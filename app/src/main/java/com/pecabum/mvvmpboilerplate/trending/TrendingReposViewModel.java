package com.pecabum.mvvmpboilerplate.trending;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.pecabum.mvvmpboilerplate.R;
import com.pecabum.mvvmpboilerplate.di.ScreenScope;
import com.pecabum.mvvmpboilerplate.model.Repo;

import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by pecabum on 22.02.18.
 */
@ScreenScope
class TrendingReposViewModel {

    private final BehaviorRelay<List<Repo>> reposRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    TrendingReposViewModel() {

    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }

    Observable<List<Repo>> repos() {
        return reposRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    Consumer<List<Repo>> reposUpdated() {
        errorRelay.accept(-1);
        return reposRelay;
    }

    Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e("Error loading repos", throwable);
            errorRelay.accept(R.string.api_error_repos);
        };
    }


}
