package com.aliesaassadi.androidmvp.data.movie.source.local;

import android.util.SparseArray;

import com.aliesaassadi.androidmvp.data.movie.Movie;
import com.aliesaassadi.androidmvp.data.movie.source.MovieDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali Esa Assadi on 30/01/2019.
 */
public class MovieCacheDataSource implements MovieDataSource {

    private static MovieCacheDataSource sInstance;

    private final SparseArray<Movie> cachedMovies = new SparseArray<>();

    public static MovieCacheDataSource getsInstance() {
        if (sInstance == null) {
            sInstance = new MovieCacheDataSource();
        }
        return sInstance;
    }

    @Override
    public void getMovies(LoadMoviesCallback callback) {

        if (cachedMovies.size() > 0) {
            List<Movie> movies = new ArrayList<>();
            for (int i = 0; i < cachedMovies.size(); i++) {
                int key = cachedMovies.keyAt(i);
                movies.add(cachedMovies.get(key));
            }

            callback.onMoviesLoaded(movies);

        } else {
            callback.onDataNotAvailable();
        }

    }

    @Override
    public void saveMovies(List<Movie> movies) {
        cachedMovies.clear();
        for (Movie movie : movies) {
            cachedMovies.put(movie.getId(), movie);
        }
    }

}
