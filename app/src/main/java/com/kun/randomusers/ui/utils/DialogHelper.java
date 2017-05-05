package com.kun.randomusers.ui.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.kun.randomusers.R;

/**
 * @author Julio Kun
 * @version 0.1
 * <p>
 *     Helper to display progress dialogs.
 * </p>
 */

public class DialogHelper {

    private ProgressDialog progressDialog;

    public void showProgressDialog(Context context) {
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.setMessage(context.getString(R.string.loading));
        progressDialog.show();
    }

    public void hideProgressDialgo() {
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
