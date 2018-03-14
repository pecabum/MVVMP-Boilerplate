package com.pecabum.mvvmpboilerplate.data;

import com.pecabum.mvvmpboilerplate.model.Contributor;
import com.pecabum.mvvmpboilerplate.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pecabum on 22.02.18.
 */

public class RepoRequester {

    private RepoService service;

    @Inject
    RepoRequester(RepoService service) {
        this.service = service;
    }

    Single<List<Repo>> getTrendingRepos() {
        return service.getTrendingRepos()
                .map(TrendingReposResponse::repos);
    }

    Single<Repo> getRepo(String owner, String name) {
        return service.getRepo(owner, name);
    }

    Single<List<Contributor>> getContributors(String url) {
        return service.getContributors(url);
    }
}
