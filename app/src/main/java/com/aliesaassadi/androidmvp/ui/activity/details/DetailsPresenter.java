package com.aliesaassadi.androidmvp.ui.activity.details;

import android.content.Intent;

import com.aliesaassadi.androidmvp.data.movie.Movie;
import com.aliesaassadi.androidmvp.ui.activity.base.BasePresenter;


/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public class DetailsPresenter extends BasePresenter<DetailsView> {

    public DetailsPresenter(DetailsView view) {
        super(view);
    }

    public void getMovieData(Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            view.showErrorMessage();
            return;
        }

        Movie movie = intent.getExtras().getParcelable("movie");

        if (movie != null) {
            view.showMovieDetails(movie.getImage(), movie.getTitle(), movie.getDescription());
        } else {
            view.showDataUnavailableMessage();
        }


    }
}
