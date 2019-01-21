package com.example.ali.androidmvp.ui.activity.main.view;

import com.example.ali.androidmvp.data.network.model.Movie;
import com.example.ali.androidmvp.data.network.model.MovieResponse;
import com.example.ali.androidmvp.ui.activity.base.BaseView;

import java.util.List;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public interface MainActivityView extends BaseView {
    void showMovies(List<Movie> movies);

    void showErrorMessage();

    void showThereIsNoMovies();
}
