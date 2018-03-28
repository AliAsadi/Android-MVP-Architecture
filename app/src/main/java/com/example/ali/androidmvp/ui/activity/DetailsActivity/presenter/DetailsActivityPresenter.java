package com.example.ali.androidmvp.ui.activity.DetailsActivity.presenter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.ali.androidmvp.data.network.model.Movie;
import com.example.ali.androidmvp.data.network.services.MovieService;
import com.example.ali.androidmvp.ui.activity.DetailsActivity.view.DetailsActivityView;
import com.example.ali.androidmvp.ui.activity.base.BasePresenter;


/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public class DetailsActivityPresenter extends BasePresenter<DetailsActivityView> {

    public DetailsActivityPresenter(DetailsActivityView view) {
        super(view);
    }

    public void getIntentExtras(Intent intent) {
        Movie movie = intent.getExtras().getParcelable("movie");
        mView.updateActivityView(movie.getImage(), movie.getTitle(), movie.getDescription());
    }
}
