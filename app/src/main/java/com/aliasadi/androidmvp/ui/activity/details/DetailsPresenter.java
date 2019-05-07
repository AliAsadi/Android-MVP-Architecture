package com.aliasadi.androidmvp.ui.activity.details;

import com.aliasadi.androidmvp.data.movie.Movie;
import com.aliasadi.androidmvp.ui.activity.base.BasePresenter;


/**
 * Created by Ali Asadi on 12/03/2018.
 */
public class DetailsPresenter extends BasePresenter<DetailsView> {

    public DetailsPresenter(DetailsView view) {
        super(view);
    }

    public void onMovieReceived(Movie movie) {
        if (movie != null) {
            view.showMovieDetails(movie);
        } else {
            view.showDataUnavailableMessage();
        }
    }
}
