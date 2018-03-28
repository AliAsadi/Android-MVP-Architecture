package com.example.ali.androidmvp.ui.activity.MainActivity.presenter;

import android.util.Log;

import com.example.ali.androidmvp.data.DataManager;
import com.example.ali.androidmvp.data.network.model.MovieResponse;
import com.example.ali.androidmvp.data.network.services.MovieService;
import com.example.ali.androidmvp.ui.activity.MainActivity.view.MainActivityView;
import com.example.ali.androidmvp.ui.activity.base.BasePresenter;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    private final MovieService mMovieService;

    public MainActivityPresenter(MainActivityView view,MovieService movieService) {
        super(view);
        mMovieService = movieService;
    }

    /**
     * Network
     **/
    public void getAllMovie() {
        Call<MovieResponse> userCall = mMovieService.getMovieApi().getAllMovie();
        userCall.enqueue(new MovieCallListener(mView));
    }


    /**
     * Callback
     **/
    private static class MovieCallListener implements Callback<MovieResponse> {

        private WeakReference<MainActivityView> mView;

        private MovieCallListener(MainActivityView view) {
            mView = new WeakReference<>(view);
        }

        @Override
        public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
            if (mView.get() != null) {
                mView.get().onGetMovie(response.body());
            }
        }

        @Override
        public void onFailure(Call<MovieResponse> call, Throwable t) {
            t.fillInStackTrace();
        }
    }
}
