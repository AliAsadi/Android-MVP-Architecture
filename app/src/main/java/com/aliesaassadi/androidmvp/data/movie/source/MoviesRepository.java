package com.aliesaassadi.androidmvp.data.movie.source;

import android.util.SparseArray;

import com.aliesaassadi.androidmvp.data.movie.Movie;
import com.aliesaassadi.androidmvp.data.movie.source.local.MovieCacheDataSource;
import com.aliesaassadi.androidmvp.data.movie.source.local.MovieLocalDataSource;
import com.aliesaassadi.androidmvp.data.movie.source.remote.MovieRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali Esa Assadi on 29/01/2019.
 */
public class MoviesRepository implements MovieDataSource {

    private final MovieDataSource movieRemoteDataSource;
    private final MovieDataSource movieLocalDataSource;
    private final MovieDataSource movieCacheDataSource;

    private static MoviesRepository instance;

    private MoviesRepository(MovieRemoteDataSource movieRemoteDataSource,
                             MovieLocalDataSource movieLocalDataSource,
                             MovieCacheDataSource movieCacheDataSource) {

        this.movieRemoteDataSource = movieRemoteDataSource;
        this.movieLocalDataSource = movieLocalDataSource;
        this.movieCacheDataSource = movieCacheDataSource;
    }

    public static MoviesRepository getInstance(MovieRemoteDataSource movieRemoteDataSource,
                                               MovieLocalDataSource movieLocalDataSource,
                                               MovieCacheDataSource movieCacheDataSource) {
        if (instance == null) {
            instance = new MoviesRepository(movieRemoteDataSource, movieLocalDataSource, movieCacheDataSource);
        }
        return instance;
    }

    @Override
    public void getMovies(final LoadMoviesCallback callback) {
        if (callback == null) return;

        movieCacheDataSource.getMovies(new LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                callback.onMoviesLoaded(movies);
            }

            @Override
            public void onDataNotAvailable() {
                getMoviesFromLocalDataSource(callback);
            }

            @Override
            public void onError() {
                //not implemented in cache data source
            }
        });

    }

    @Override
    public void saveMovies(List<Movie> movies) {
        movieLocalDataSource.saveMovies(movies);
    }

    private void getMoviesFromLocalDataSource(final LoadMoviesCallback callback) {
        movieLocalDataSource.getMovies(new LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                callback.onMoviesLoaded(movies);
                refreshCache(movies);
            }

            @Override
            public void onDataNotAvailable() {
                getMoviesFromRemoteDataSource(callback);
            }

            @Override
            public void onError() {
                //not implemented in local data source
            }
        });
    }

    private void getMoviesFromRemoteDataSource(final LoadMoviesCallback callback) {
        movieRemoteDataSource.getMovies(new LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                callback.onMoviesLoaded(movies);
                saveMovies(movies);
                refreshCache(movies);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    private void refreshCache(List<Movie> movies) {
        movieCacheDataSource.saveMovies(movies);
    }


}
