package com.aliesaassadi.androidmvp.data.movie.source.remote.model;

import com.aliesaassadi.androidmvp.data.movie.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ali Esa Assadi on 24/03/2018.
 */

public class MovieResponse {

    @Expose
    @SerializedName("movies")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
