package com.aliasadi.androidmvp.ui.activity.main;

import com.aliasadi.androidmvp.data.movie.Movie;
import com.aliasadi.androidmvp.ui.activity.base.BaseView;

import java.util.List;

/**
 * Created by Ali Asadi on 12/03/2018.
 */
public interface MainView extends BaseView {
    void showMovies(List<Movie> movies);

    void showErrorMessage();

    void showThereIsNoMovies();
}
