package com.pecabum.mvvmpboilerplate.data;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by pecabum on 22.02.18.
 */

public interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();

}
