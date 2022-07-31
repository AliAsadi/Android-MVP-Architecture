package com.aliasadi.androidmvp.data.movie.source.remote.services;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ali Asadi on 26/03/2018.
 */
public class MovieService {

    private static final String URL = "https://demo7812962.mockable.io/";

    private MovieApi mMovieApi;

    private static MovieService singleton;

    private MovieService() {
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
        mMovieApi = mRetrofit.create(MovieApi.class);
    }

    public static MovieService getInstance() {
        if (singleton == null) {
            singleton = new MovieService();
        }
        return singleton;
    }

    public MovieApi getMovieApi() {
        return mMovieApi;
    }

}
