package com.aliesaassadi.androidmvp.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aliesaassadi.androidmvp.R;
import com.aliesaassadi.androidmvp.data.DataManager;
import com.aliesaassadi.androidmvp.data.movie.MoviesRepository;
import com.aliesaassadi.androidmvp.data.movie.local.MovieLocalDataSource;
import com.aliesaassadi.androidmvp.data.movie.local.dao.MovieDao;
import com.aliesaassadi.androidmvp.data.movie.local.database.MovieDatabase;
import com.aliesaassadi.androidmvp.data.movie.remote.MovieRemoteDataSource;
import com.aliesaassadi.androidmvp.data.movie.Movie;
import com.aliesaassadi.androidmvp.data.movie.remote.services.MovieApi;
import com.aliesaassadi.androidmvp.ui.activity.base.BaseActivity;
import com.aliesaassadi.androidmvp.ui.activity.details.DetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
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

        MovieApi movieApi = DataManager.getInstance().getMovieApi();
        MovieRemoteDataSource remoteDataSource = MovieRemoteDataSource.getInstance(movieApi);

        MovieDao movieDao = MovieDatabase.getInstance().movieDao();
        MovieLocalDataSource localDataSource = MovieLocalDataSource.getInstance(movieDao);

        MoviesRepository movieRepository = DataManager.getInstance()
                .getMovieRepository(remoteDataSource, localDataSource);

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
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);

    }
}
