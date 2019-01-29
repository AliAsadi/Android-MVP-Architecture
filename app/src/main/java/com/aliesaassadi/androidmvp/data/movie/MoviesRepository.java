package com.aliesaassadi.androidmvp.data.movie;

/**
 * Created by Ali Esa Assadi on 29/01/2019.
 */
public class MoviesRepository implements MovieDataSource {

    private final MovieDataSource movieRemoteDataSource;

    private static MoviesRepository instance;

    private MoviesRepository(MovieDataSource movieRemoteDataSource) {
        this.movieRemoteDataSource = movieRemoteDataSource;
    }

    public static MoviesRepository getInstance(MovieDataSource movieRemoteDataSource) {
        if (instance == null) {
            instance = new MoviesRepository(movieRemoteDataSource);
        }
        return instance;
    }


    @Override
    public void getMovies(LoadMoviesCallback callback) {
        movieRemoteDataSource.getMovies(callback);
    }
}
