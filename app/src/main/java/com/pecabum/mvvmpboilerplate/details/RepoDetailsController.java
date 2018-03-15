package com.pecabum.mvvmpboilerplate.details;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pecabum.mvvmpboilerplate.R;
import com.pecabum.mvvmpboilerplate.base.BaseController;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by pecabum on 02.03.18.
 */

public class RepoDetailsController extends BaseController {

    static final String KEY_REPO_NAME = "repo_name";
    static final String KEY_REPO_OWNER = "repo_owner";

    @Inject
    RepoDetailsViewModel viewModel;
    @Inject
    RepoDetailsPresenter presenter;

    @BindView(R.id.tv_repo_name)
    TextView repoNameText;
    @BindView(R.id.tv_repo_description)
    TextView repoDescriptionText;
    @BindView(R.id.tv_creation_date)
    TextView createdDateText;
    @BindView(R.id.tv_updated_date)
    TextView updatedDateText;
    @BindView(R.id.contributor_list)
    RecyclerView contributorList;
    @BindView(R.id.loading_indicator)
    View detailsLoadingView;
    @BindView(R.id.contributor_loading_indicator)
    View contributorsLoadingView;
    @BindView(R.id.content)
    View contentContainer;
    @BindView(R.id.tv_error)
    TextView errorText;
    @BindView(R.id.tv_contributors_error)
    TextView contributorsErrorText;

    public static RepoDetailsController newInstance(String repoName, String repoOwner) {

        Bundle args = new Bundle();

        args.putString(KEY_REPO_NAME, repoName);
        args.putString(KEY_REPO_OWNER, repoOwner);

        return new RepoDetailsController(args);
    }

    public RepoDetailsController(Bundle bundle) {
        super(bundle);
    }


    @Override
    protected void onViewBound(View view) {
        contributorList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        contributorList.setAdapter(new ContributorAdapter());
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.details()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(details -> {
                    if (details.loading()) {
                        detailsLoadingView.setVisibility(View.VISIBLE);
                        contentContainer.setVisibility(View.GONE);
                        errorText.setVisibility(View.GONE);
                        errorText.setText(null);
                    } else {
                        if (details.isSuccess()) {
                            errorText.setText(null);
                        } else {
                            //noinspection ConstantConditions
                            errorText.setText(details.errorRes());
                        }
                        detailsLoadingView.setVisibility(View.GONE);
                        contentContainer.setVisibility(details.isSuccess() ? View.VISIBLE : View.GONE);
                        errorText.setVisibility(details.isSuccess() ? View.GONE : View.VISIBLE);
                        repoNameText.setText(details.name());
                        repoDescriptionText.setText(details.description());
                        createdDateText.setText(details.createdDate());
                        updatedDateText.setText(details.updatedDate());
                    }
                }),
                viewModel.contributors()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(contributorsDetails -> {
                    if (contributorsDetails.loading()) {
                        contributorsLoadingView.setVisibility(View.VISIBLE);
                        contributorList.setVisibility(View.GONE);
                        contributorsErrorText.setVisibility(View.GONE);
                        contributorsErrorText.setText(null);
                    } else {
                        contributorsLoadingView.setVisibility(View.GONE);
                        contributorList.setVisibility(contributorsDetails.isSuccess() ? View.VISIBLE : View.GONE);
                        contributorsErrorText.setVisibility(contributorsDetails.isSuccess() ? View.GONE : View.VISIBLE);
                        if (contributorsDetails.isSuccess()) {
                            contributorsErrorText.setText(null);
                            ((ContributorAdapter) contributorList.getAdapter()).setData(contributorsDetails.contributors());
                        } else {
                            //noinspection ConstantConditions
                            contributorsErrorText.setText(contributorsDetails.errorRes());
                        }
                    }
                })
        };
    }


    @Override
    protected int layoutRes() {
        return R.layout.screen_repo_details;
    }
}
