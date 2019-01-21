package com.aliesaassadi.androidmvp.ui.activity.main;

import com.aliesaassadi.androidmvp.data.network.model.Movie;
import com.aliesaassadi.androidmvp.ui.activity.base.BaseView;

import java.util.List;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public interface MainView extends BaseView {
    void showMovies(List<Movie> movies);

    void showErrorMessage();

    void showThereIsNoMovies();
}
