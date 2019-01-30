package com.aliesaassadi.androidmvp.data.movie.remote.services;

import com.aliesaassadi.androidmvp.data.movie.remote.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("movies/")
    Call<MovieResponse> getMovies();
}
