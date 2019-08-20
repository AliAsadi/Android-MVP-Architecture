package com.aliasadi.androidmvp.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aliasadi.androidmvp.R;
import com.aliasadi.androidmvp.data.DataManager;
import com.aliasadi.androidmvp.data.movie.source.MoviesRepository;
import com.aliasadi.androidmvp.data.movie.Movie;
import com.aliasadi.androidmvp.ui.base.BaseActivity;
import com.aliasadi.androidmvp.ui.details.DetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ali Asadi on 12/03/2018.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainView, MovieAdapter.OnMovieAdapter {

    MovieAdapter movieAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);
        presenter.getAllMovie();
    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        MoviesRepository movieRepository = DataManager.getInstance().getMovieRepository();
        return new MainPresenter(this, movieRepository);
    }


    @Override
    public void showMovies(List<Movie> movies) {
        movieAdapter.setItems(movies);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "Server error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showThereIsNoMovies() {
        Toast.makeText(this, "There is no movies!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieClicked(Movie movie) {
        startActivity(DetailsActivity.newIntent(this, movie));
    }
}
