package com.pecabum.mvvmpboilerplate.details;

import com.pecabum.mvvmpboilerplate.data.RepoRepository;
import com.pecabum.mvvmpboilerplate.di.ScreenScope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by pecabum on 14.03.18.
 */


@ScreenScope
class RepoDetailsPresenter {

    @Inject
    RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailsViewModel viewModel) {
        repoRepository.getRepo(repoOwner, repoName)
                .doOnSuccess(viewModel.processRepo())
                .doOnError(viewModel.detailsError())
                .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
                        .doOnError(viewModel.contributorsError()))
                .subscribe(viewModel.processContributors(), throwable -> {
                    // We handle logging in the view model
                });
    }
}
