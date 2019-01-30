package com.aliesaassadi.androidmvp.ui.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Ali Esa Assadi on 26/03/2018.
 */
public abstract class BasePresenter<View extends BaseView> {

    protected View view;

    protected BasePresenter(View view) {
        this.view = view;
    }

    @CallSuper
    void onDestroy() {
        //avoid memory leak
        view = null;
    }

}
