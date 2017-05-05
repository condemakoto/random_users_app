package com.kun.randomusers.presenter;

import com.kun.randomusers.presenter.view.BaseView;

/**
 * @author Julio Kun
 * @version
 */

public abstract class BasePresenter <T extends BaseView> {

    private T view;

    public void addView(T view) {
        this.view = view;
    }

    protected T getView() {
        return view;
    }

    public void onCreate() {

    }

    public void onStart() {

    }

    public void onPause() {

    }

    public void onDestroy() {
        this.view = null;
    }

}
