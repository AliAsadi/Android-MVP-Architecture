package com.aliesaassadi.androidmvp.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aliesaassadi.androidmvp.R;
import com.aliesaassadi.androidmvp.data.DataManager;
import com.aliesaassadi.androidmvp.data.movie.source.MoviesRepository;
import com.aliesaassadi.androidmvp.data.movie.source.local.MovieCacheDataSource;
import com.aliesaassadi.androidmvp.data.movie.source.local.MovieLocalDataSource;
import com.aliesaassadi.androidmvp.data.movie.source.local.dao.MovieDao;
import com.aliesaassadi.androidmvp.data.movie.source.local.database.MovieDatabase;
import com.aliesaassadi.androidmvp.data.movie.source.remote.MovieRemoteDataSource;
import com.aliesaassadi.androidmvp.data.movie.Movie;
import com.aliesaassadi.androidmvp.data.movie.source.remote.services.MovieApi;
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

        MovieCacheDataSource cacheDataSource = MovieCacheDataSource.getsInstance();

        MoviesRepository movieRepository = DataManager.getInstance()
                .getMovieRepository(remoteDataSource, localDataSource, cacheDataSource);

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
