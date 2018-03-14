package com.pecabum.mvvmpboilerplate.data;

import com.pecabum.mvvmpboilerplate.model.Repo;
import com.pecabum.mvvmpboilerplate.testutils.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by pecabum on 13.03.18.
 */
public class RepoRepositoryTest {

    @Mock
    Provider<RepoRequester> repoRequesterProvider;
    @Mock
    RepoRequester repoRequester;

    private RepoRepository repository;
    private TrendingReposResponse trendingReposResponse;
    private Repo rxJavaRepo;
    private Repo otherRepo;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        trendingReposResponse = TestUtils.loadJson("mock/get_trending_repos.json", TrendingReposResponse.class);

        when(repoRequester.getTrendingRepos()).thenReturn(Single.just(trendingReposResponse.repos()));

        rxJavaRepo = trendingReposResponse.repos().get(0);
        otherRepo = trendingReposResponse.repos().get(1);

        repository = new RepoRepository(repoRequesterProvider, Schedulers.trampoline());
    }

    @Test
    public void getTrendingRepos() throws Exception {
        repository.getTrendingRepos().test().assertValue(trendingReposResponse.repos());

        // Create a different list and have the API call return that on subsequent calls
        List<Repo> modifiedList = new ArrayList<>(trendingReposResponse.repos());
        modifiedList.remove(0);
        when(repoRequester.getTrendingRepos()).thenReturn(Single.just(modifiedList));

        // Verify we still get the cached list rather than the different API response
        repository.getTrendingRepos().test().assertValue(trendingReposResponse.repos());
    }

    @Test
    public void getRepo() throws Exception {
        // Load trending repos to mimic most likely state of app
        repository.getTrendingRepos().subscribe();

        // Change requester to return a different repo if ever invoked
        when(repoRequester.getRepo(anyString(), anyString())).thenReturn(Single.just(otherRepo));

        // Verify we still get the RxJava repo, which is cached from our trending repos call above
        repository.getRepo("ReactiveX", "RxJava").test().assertValue(rxJavaRepo);

        // Fetch a repo that would not be in the cache and verify the API result is returned
        repository.getRepo("NotInCache", "NotInCache").test().assertValue(otherRepo);
    }

}