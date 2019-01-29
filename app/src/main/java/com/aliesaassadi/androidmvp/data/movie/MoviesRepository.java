package com.aliesaassadi.androidmvp.data.movie;

import java.util.List;

/**
 * Created by Ali Esa Assadi on 29/01/2019.
 */
public class MoviesRepository implements MovieDataSource {

    private final MovieDataSource movieRemoteDataSource;

    private static MoviesRepository instance;
    private final MovieDataSource movieLocalDataSource;

    private MoviesRepository(MovieDataSource movieRemoteDataSource,
                             MovieDataSource movieLocalDataSource) {
        this.movieRemoteDataSource = movieRemoteDataSource;
        this.movieLocalDataSource = movieLocalDataSource;
    }

    public static MoviesRepository getInstance(MovieDataSource movieRemoteDataSource,
                                               MovieDataSource movieLocalDataSource) {
        if (instance == null) {
            instance = new MoviesRepository(movieRemoteDataSource,movieLocalDataSource);
        }
        return instance;
    }

    @Override
    public void getMovies(final LoadMoviesCallback callback) {
        movieLocalDataSource.getMovies(new LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                callback.onMoviesLoaded(movies);
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

    @Override
    public void saveMovies(List<Movie> movies) {
        movieLocalDataSource.saveMovies(movies);
    }

    private void getMoviesFromRemoteDataSource(final LoadMoviesCallback callback) {
        movieRemoteDataSource.getMovies(new LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                callback.onMoviesLoaded(movies);
                saveMovies(movies);
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

}
