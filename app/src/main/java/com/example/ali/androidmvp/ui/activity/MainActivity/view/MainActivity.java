package com.example.ali.androidmvp.ui.activity.MainActivity.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ali.androidmvp.R;
import com.example.ali.androidmvp.data.DataManager;
import com.example.ali.androidmvp.data.network.model.Movie;
import com.example.ali.androidmvp.data.network.model.MovieResponse;
import com.example.ali.androidmvp.data.network.services.MovieService;
import com.example.ali.androidmvp.ui.activity.DetailsActivity.view.DetailsActivity;
import com.example.ali.androidmvp.ui.activity.MainActivity.presenter.MainActivityPresenter;
import com.example.ali.androidmvp.ui.activity.MainActivity.presenter.MovieAdapter;
import com.example.ali.androidmvp.ui.activity.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */

public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityView, MovieAdapter.OnMovieAdapter {

    MovieAdapter mMovieAdapter;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);
        mPresenter.getAllMovie();
    }

    @NonNull
    @Override
    protected MainActivityPresenter createPresenter() {
        return new MainActivityPresenter(this, mDataManager.getMovieService());
    }


    @Override
    public void onGetMovie(MovieResponse response) {
        mMovieAdapter.setItems(response.getMovies());
    }

    @Override
    public void onMovieClicked(Movie movie) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);

    }
}
