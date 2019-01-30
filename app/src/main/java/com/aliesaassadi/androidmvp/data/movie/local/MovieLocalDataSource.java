package com.aliesaassadi.androidmvp.data.movie.local;

import com.aliesaassadi.androidmvp.data.movie.Movie;
import com.aliesaassadi.androidmvp.data.movie.MovieDataSource;
import com.aliesaassadi.androidmvp.data.movie.local.dao.MovieDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieLocalDataSource implements MovieDataSource {

    private Executor executor;
    private MovieDao movieDao;

    private static MovieLocalDataSource instance;

    private MovieLocalDataSource(Executor executor, MovieDao movieDao) {
        this.executor = executor;
        this.movieDao = movieDao;
    }

    public static MovieLocalDataSource getInstance(MovieDao movieDao) {
        if (instance == null) {
            instance = new MovieLocalDataSource(Executors.newSingleThreadExecutor(), movieDao);
        }
        return instance;
    }

    @Override
    public void getMovies(final LoadMoviesCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<Movie> movies = movieDao.getMovies();
                if (!movies.isEmpty()) {
                    callback.onMoviesLoaded(movies);
                } else {
                    callback.onDataNotAvailable();
                }
            }
        };
        executor.execute(runnable);
    }

    @Override
    public void saveMovies(final List<Movie> movies) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                movieDao.saveMovies(movies);
            }
        };
        executor.execute(runnable);
    }
}
