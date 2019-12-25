package com.aliasadi.androidmvp.ui.details;

import android.content.Intent;

import com.aliasadi.androidmvp.data.movie.Movie;
import com.aliasadi.androidmvp.ui.base.BasePresenter;


/**
 * Created by Ali Asadi on 12/03/2018.
 */
public class DetailsPresenter extends BasePresenter<DetailsView> {

    static final String KEY_MOVIE = "movie";

    private final Intent intent;

    DetailsPresenter(DetailsView view, Intent intent) {
        super(view);
        this.intent = intent;
    }

    public void onCreateView() {
        showMovieData();
    }

    private void showMovieData() {
        Movie movie = getMovieFromBundle();
        if (movie != null) {
            view.showMovieData(movie);
        } else {
            view.showDataUnavailableMessage();
        }
    }

    private Movie getMovieFromBundle() {
        return intent.getParcelableExtra(KEY_MOVIE);
    }

}
