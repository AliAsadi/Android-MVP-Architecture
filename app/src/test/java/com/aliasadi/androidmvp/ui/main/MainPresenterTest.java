package com.aliasadi.androidmvp.ui.main;

import com.aliasadi.androidmvp.data.movie.source.MovieDataSource;
import com.aliasadi.androidmvp.data.movie.source.MoviesRepository;
import com.aliasadi.androidmvp.data.movie.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ali Asadi on 21/01/2019.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private MainView view;
    @Mock
    private MoviesRepository moviesRepository;

    @Captor
    private ArgumentCaptor<MovieDataSource.LoadMoviesCallback> loadMoviesCallback;

    private MainPresenter presenter;

    @Before
    public void setUp() {
        presenter = new MainPresenter(view, moviesRepository);
    }

    @Test
    public void showMovies_WhenGetMoviesCallSuccess() {

        presenter.getAllMovie();

        List<Movie> movies = Arrays.asList(new Movie(), new Movie());

        Mockito.verify(moviesRepository).getMovies(loadMoviesCallback.capture());
        loadMoviesCallback.getValue().onMoviesLoaded(movies);

        Mockito.verify(view).showMovies((List<Movie>) Mockito.any());
    }

    @Test
    public void showNoMovies_WhenGetMoviesCallSuccessAndMovieListIsEmpty() {

        presenter.getAllMovie();

        Mockito.verify(moviesRepository).getMovies(loadMoviesCallback.capture());
        loadMoviesCallback.getValue().onDataNotAvailable();

        Mockito.verify(view).showThereIsNoMovies();
    }

    @Test
    public void showErrorMessage_WhenGetMoviesCallFailed() {
        presenter.getAllMovie();

        Mockito.verify(moviesRepository).getMovies(loadMoviesCallback.capture());
        loadMoviesCallback.getValue().onError();

        Mockito.verify(view).showErrorMessage();
    }

}