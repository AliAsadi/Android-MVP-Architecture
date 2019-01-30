package com.aliesaassadi.androidmvp.data.movie.source;

import com.aliesaassadi.androidmvp.data.movie.Movie;

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
