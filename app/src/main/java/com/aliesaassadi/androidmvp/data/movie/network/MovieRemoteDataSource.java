package com.aliesaassadi.androidmvp.data.movie.network;

import com.aliesaassadi.androidmvp.data.movie.MovieDataSource;
import com.aliesaassadi.androidmvp.data.movie.network.model.Movie;
import com.aliesaassadi.androidmvp.data.movie.network.model.MovieResponse;
import com.aliesaassadi.androidmvp.data.movie.network.services.MovieApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRemoteDataSource implements MovieDataSource {

    private static MovieRemoteDataSource instance;

    private final MovieApi movieApi;

    private MovieRemoteDataSource(MovieApi movieApi) {
        this.movieApi = movieApi;
    }

    public static MovieRemoteDataSource getInstance(MovieApi movieApi) {
        if (instance == null) {
            instance = new MovieRemoteDataSource(movieApi);
        }
        return instance;
    }

    @Override
    public void getMovies(final LoadMoviesCallback callback) {
        movieApi.getAllMovie().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body() != null ? response.body().getMovies() : null;
                if (movies != null && !movies.isEmpty()) {
                    callback.onMoviesLoaded(movies);
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                callback.onError();
            }
        });
    }
}
