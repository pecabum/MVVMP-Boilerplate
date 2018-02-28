package com.pecabum.mvvmpboilerplate.trending;

import com.pecabum.mvvmpboilerplate.data.RepoRequester;
import com.pecabum.mvvmpboilerplate.di.ScreenScope;
import com.pecabum.mvvmpboilerplate.model.Repo;

import javax.inject.Inject;

/**
 * Created by pecabum on 28.02.18.
 */

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private final TrendingReposViewModel viewModel;
    private final RepoRequester repoRequester;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRequester repoRequester) {
        this.viewModel = viewModel;
        this.repoRequester = repoRequester;
        loadRepos();
    }

    private void loadRepos() {
        repoRequester.getTrendingRepos()
                .doOnSubscribe(disposable -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((repos, throwable) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(),viewModel.onError());
    }

    @Override
    public void onRepoClicked(Repo repo) {

    }
}
