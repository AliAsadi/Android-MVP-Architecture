package com.aliesaassadi.androidmvp.data.movie;

import java.util.List;

public interface MovieDataSource {


    interface LoadMoviesCallback {
        void onMoviesLoaded(List<Movie> movies);
        void onDataNotAvailable();
        void onError();
    }

    void getMovies(LoadMoviesCallback callback);
    void saveMovies(List<Movie> movies);
}
