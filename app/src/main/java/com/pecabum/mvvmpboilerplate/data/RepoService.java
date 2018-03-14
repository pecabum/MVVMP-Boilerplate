package com.pecabum.mvvmpboilerplate.data;

import com.pecabum.mvvmpboilerplate.model.Contributor;
import com.pecabum.mvvmpboilerplate.model.Repo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by pecabum on 22.02.18.
 */

public interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();

    @GET("repos/{owner}/{name}")
    Single<Repo> getRepo(@Path("owner")String owner, @Path("name") String name);

    @GET
    Single<List<Contributor>> getContributors (@Url String url);


}
