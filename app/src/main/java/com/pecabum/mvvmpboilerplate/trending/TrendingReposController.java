package com.pecabum.mvvmpboilerplate.trending;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;
import com.pecabum.mvvmpboilerplate.R;
import com.pecabum.mvvmpboilerplate.base.BaseController;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by ppetrov on 21.02.18.
 */

public class TrendingReposController extends BaseController {

    @Inject
    TrendingReposPresenter trendingReposPresenter;
    @Inject
    TrendingReposViewModel trendingReposViewModel;

    @BindView(R.id.rv_repos)
    RecyclerView rvRepos;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.tv_error)
    TextView tvError;

    @Override
    protected void onViewBound(View view) {
        rvRepos.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvRepos.setAdapter(new RepoAdapter(trendingReposPresenter));
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                trendingReposViewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
                    rvRepos.setVisibility(loading ? View.GONE : View.VISIBLE);
                    tvError.setVisibility(loading ? View.GONE : tvError.getVisibility());
                }),
                trendingReposViewModel.repos()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(((RepoAdapter) rvRepos.getAdapter())::setData),
                trendingReposViewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(errorRes -> {
                    if (errorRes == -1) {
                        tvError.setText(null);
                        tvError.setVisibility(View.GONE);
                    } else {
                        tvError.setVisibility(View.VISIBLE);
                        rvRepos.setVisibility(View.GONE);
                        tvError.setText(errorRes);
                    }
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_trending_repos;
    }
}
