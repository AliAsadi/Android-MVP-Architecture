package com.example.ali.androidmvp.ui.activity.MainActivity.view;

import com.example.ali.androidmvp.data.network.model.MovieResponse;
import com.example.ali.androidmvp.ui.activity.base.BaseView;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public interface MainActivityView extends BaseView {
    void onGetMovie(MovieResponse movies);
}
