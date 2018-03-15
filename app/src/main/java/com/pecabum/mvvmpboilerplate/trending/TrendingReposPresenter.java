package com.pecabum.mvvmpboilerplate.trending;

import com.pecabum.mvvmpboilerplate.data.RepoRepository;
import com.pecabum.mvvmpboilerplate.data.RepoRequester;
import com.pecabum.mvvmpboilerplate.di.ScreenScope;
import com.pecabum.mvvmpboilerplate.model.Repo;
import com.pecabum.mvvmpboilerplate.ui.ScreenNavigator;

import javax.inject.Inject;

/**
 * Created by pecabum on 28.02.18.
 */

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;
    private ScreenNavigator navigator;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRepository repoRequester, ScreenNavigator navigator) {
        this.viewModel = viewModel;
        this.repoRepository= repoRequester;
        this.navigator = navigator;
        loadRepos();
    }

    private void loadRepos() {
        repoRepository.getTrendingRepos()
                .doOnSubscribe(disposable -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((repos, throwable) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(),viewModel.onError());
    }

    @Override
    public void onRepoClicked(Repo repo) {
        navigator.goToRepoDetails(repo.owner().login(),repo.name());
    }
}
