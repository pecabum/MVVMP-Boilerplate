package com.pecabum.mvvmpboilerplate.details;

import android.os.Bundle;

import com.pecabum.mvvmpboilerplate.R;
import com.pecabum.mvvmpboilerplate.base.BaseController;

/**
 * Created by pecabum on 02.03.18.
 */

public class RepoDetailsController extends BaseController {

    static final String KEY_REPO_NAME = "repo_name";
    static final String KEY_REPO_OWNER = "repo_owner";


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
    protected int layoutRes() {
        return R.layout.screen_repo_details;
    }
}
