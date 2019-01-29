package com.aliesaassadi.androidmvp.data.movie.db;

import com.aliesaassadi.androidmvp.data.movie.db.dao.MovieDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieLocalDataSource {

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

}
