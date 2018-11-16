package com.example.ali.androidmvp.ui.activity.details.presenter;

import android.content.Intent;

import com.example.ali.androidmvp.data.network.model.Movie;
import com.example.ali.androidmvp.ui.activity.details.view.DetailsActivityView;
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
