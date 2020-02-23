package com.aliasadi.androidmvp.ui.details;

import com.aliasadi.androidmvp.data.movie.Movie;
import com.aliasadi.androidmvp.ui.base.BasePresenter;


/**
 * Created by Ali Asadi on 12/03/2018.
 */
public class DetailsPresenter extends BasePresenter<DetailsView> {

    private final Movie movie;

    DetailsPresenter(DetailsView view, Movie movie) {
        super(view);
        this.movie = movie;
    }

    public void onAttach() {
        showMovieData();
    }

    private void showMovieData() {
        if (movie != null) {
            view.showMovieData(movie);
        } else {
            view.showDataUnavailableMessage();
        }
    }
}
