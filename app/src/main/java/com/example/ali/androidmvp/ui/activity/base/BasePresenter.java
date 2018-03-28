package com.example.ali.androidmvp.ui.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Ali Esa Assadi on 26/03/2018.
 */
public abstract class BasePresenter<View extends BaseView> {

    protected View mView;

    protected BasePresenter(View view) {
        mView = view;
    }

    @CallSuper
    public void onCreate(@Nullable final Bundle savedInstanceState) {
    }


    @CallSuper
    public void onResume() {
    }

    @CallSuper
    public void onPause() {
    }

    @CallSuper
    public void onSaveInstanceState(@NonNull final Bundle outState) {
    }

    @CallSuper
    public void onDestroy() {
        //avoid memory leak
        mView = null;
    }

    @CallSuper
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
    }

    @CallSuper
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {}
}
