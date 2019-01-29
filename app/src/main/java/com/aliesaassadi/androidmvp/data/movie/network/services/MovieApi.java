package com.aliesaassadi.androidmvp.data.movie.network.services;

import com.aliesaassadi.androidmvp.data.movie.network.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("movies/")
    Call<MovieResponse> getMovies();
}
