package com.aliesaassadi.androidmvp;

import com.aliesaassadi.androidmvp.data.movie.network.model.Movie;
import com.aliesaassadi.androidmvp.data.movie.network.model.MovieResponse;
import com.aliesaassadi.androidmvp.data.movie.network.services.MovieService;
import com.aliesaassadi.androidmvp.ui.activity.main.MainPresenter;
import com.aliesaassadi.androidmvp.ui.activity.main.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ali Esa Assadi on 21/01/2019.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainView view;

    @Mock MovieService movieService;

    MainPresenter presenter;

    @Before
    public void setUp() {
        MovieService.MovieApi movieApi = Mockito.mock(MovieService.MovieApi.class);
        Mockito.when(movieService.getMovieApi()).thenReturn(movieApi);

        presenter = new MainPresenter(view, movieService);
    }

    @Test
    public void showMovies_WhenGetMoviesCallSuccess() {

        final Call<MovieResponse> movieCall = Mockito.mock(Call.class);
        Mockito.when(movieService.getMovieApi().getAllMovie()).thenReturn(movieCall);

        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        Mockito.doAnswer(new ResponseSuccess(movies)).when(movieCall).enqueue(Mockito.any(Callback.class));

        presenter.getAllMovie();
        Mockito.verify(view).showMovies((List<Movie>) Mockito.any());
    }

    @Test
    public void showNoMovies_WhenGetMoviesCallSuccessAndMovieListIsEmpty() {

        final Call<MovieResponse> movieCall = Mockito.mock(Call.class);
        Mockito.when(movieService.getMovieApi().getAllMovie()).thenReturn(movieCall);

        Mockito.doAnswer(new ResponseSuccess(Collections.EMPTY_LIST)).when(movieCall).enqueue(Mockito.any(Callback.class));

        presenter.getAllMovie();
        Mockito.verify(view).showThereIsNoMovies();
    }

    @Test
    public void showErrorMessage_WhenGetMoviesCallFailed() {

        final Call<MovieResponse> movieCall = Mockito.mock(Call.class);
        Mockito.when(movieService.getMovieApi().getAllMovie()).thenReturn(movieCall);

        Mockito.doAnswer(new ResponseError()).when(movieCall).enqueue(Mockito.any(Callback.class));

        presenter.getAllMovie();
        Mockito.verify(view).showErrorMessage();
    }


    private class ResponseSuccess implements Answer {

        private final List<Movie> list;

        ResponseSuccess(List<Movie> list) {
            this.list = list;
        }

        @Override
        public Object answer(InvocationOnMock invocation) throws Throwable {
            Callback<MovieResponse> callback = invocation.getArgument(0);

            MovieResponse movieResponse = new MovieResponse();
            movieResponse.setMovies(list);

            Response<MovieResponse> response = Response.success(movieResponse);
            callback.onResponse(null, response);
            return null;
        }
    }

    private class ResponseError implements Answer {

        @Override
        public Object answer(InvocationOnMock invocation) throws Throwable {
            Callback<MovieResponse> callback = invocation.getArgument(0);
            callback.onFailure(null, new Exception());
            return null;
        }
    }


}