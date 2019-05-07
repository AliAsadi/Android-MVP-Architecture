package com.aliasadi.androidmvp.ui.activity.main;

import com.aliasadi.androidmvp.data.movie.source.MovieDataSource;
import com.aliasadi.androidmvp.data.movie.source.MoviesRepository;
import com.aliasadi.androidmvp.data.movie.Movie;
import com.aliasadi.androidmvp.ui.activity.base.BasePresenter;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Ali Asadi on 12/03/2018.
 */
public class MainPresenter extends BasePresenter<MainView> {

    private final MoviesRepository movieRepository;

    public MainPresenter(MainView view, MoviesRepository movieRepository) {
        super(view);
        this.movieRepository = movieRepository;
    }

    /**
     * Network
     **/
    public void getAllMovie() {
        movieRepository.getMovies(new MovieCallListener(view));
    }

    /**
     * Callback
     **/
    private static class MovieCallListener implements MovieDataSource.LoadMoviesCallback {

        private WeakReference<MainView> view;

        private MovieCallListener(MainView view) {
            this.view = new WeakReference<>(view);
        }

        @Override
        public void onMoviesLoaded(List<Movie> movies) {
            if (view.get() == null) return;
            view.get().showMovies(movies);
        }

        @Override
        public void onDataNotAvailable() {
            if (view.get() == null) return;
            view.get().showThereIsNoMovies();

        }

        @Override
        public void onError() {
            if (view.get() == null) return;
            view.get().showErrorMessage();

        }
    }
}
