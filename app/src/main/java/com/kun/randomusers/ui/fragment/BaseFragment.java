package com.kun.randomusers.ui.fragment;

import android.support.v4.app.Fragment;

import com.kun.randomusers.ui.utils.DialogHelper;

/**
 * @author Julio Kun
 * @version 0.1
 */

public abstract class BaseFragment extends Fragment {

    private DialogHelper dialogHelper;

    public BaseFragment() {
        this.dialogHelper = new DialogHelper();
    }


    public void showProgress() {
        dialogHelper.showProgressDialog(getActivity());
    }

    public void hideProgress() {
        dialogHelper.hideProgressDialgo();
    }

}
