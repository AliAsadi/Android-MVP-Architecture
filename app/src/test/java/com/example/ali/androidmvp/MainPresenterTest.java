package com.example.ali.androidmvp;

import com.example.ali.androidmvp.data.network.model.Movie;
import com.example.ali.androidmvp.data.network.model.MovieResponse;
import com.example.ali.androidmvp.data.network.services.MovieService;
import com.example.ali.androidmvp.ui.activity.main.presenter.MainActivityPresenter;
import com.example.ali.androidmvp.ui.activity.main.view.MainActivityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock MainActivityView view;

    @Mock MovieService movieService;

    MainActivityPresenter presenter;

    @Before
    public void setUp() {
        MovieService.MovieApi movieApi = Mockito.mock(MovieService.MovieApi.class);
        Mockito.when(movieService.getMovieApi()).thenReturn(movieApi);

        presenter = new MainActivityPresenter(view, movieService);
    }

    @Test
    public void showMovies_WhenGetMoviesCallSuccess() {

        final Call<MovieResponse> movieCall = Mockito.mock(Call.class);

        Mockito.when(movieService.getMovieApi().getAllMovie()).thenReturn(movieCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<MovieResponse> callback = invocation.getArgument(0);

                MovieResponse movieResponse = new MovieResponse();

                List<Movie> movies = new ArrayList<>();
                movies.add(new Movie());
                movies.add(new Movie());
                movieResponse.setMovies(movies);

                Response<MovieResponse> response = Response.success(movieResponse);

                callback.onResponse(movieCall, response);

                return null;
            }
        }).when(movieCall).enqueue(Mockito.any(Callback.class));

        presenter.getAllMovie();
        Mockito.verify(view).showMovies((List<Movie>) Mockito.any());
    }

    @Test
    public void showNoMovies_WhenGetMoviesCallSuccessAndMovieListIsEmpty() {

        final Call<MovieResponse> movieCall = Mockito.mock(Call.class);

        Mockito.when(movieService.getMovieApi().getAllMovie()).thenReturn(movieCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<MovieResponse> callback = invocation.getArgument(0);

                Response<MovieResponse> response = Response.success(new MovieResponse());

                callback.onResponse(movieCall, response);

                return null;
            }
        }).when(movieCall).enqueue(Mockito.any(Callback.class));

        presenter.getAllMovie();
        Mockito.verify(view).showThereIsNoMovies();
    }

    @Test
    public void showErrorMessage_WhenGetMoviesCallFailed() {
        final Call<MovieResponse> movieCall = Mockito.mock(Call.class);

        Mockito.when(movieService.getMovieApi().getAllMovie()).thenReturn(movieCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<MovieResponse> callback = invocation.getArgument(0);
                callback.onFailure(movieCall, new Exception());
                return null;
            }
        }).when(movieCall).enqueue(Mockito.any(Callback.class));

        presenter.getAllMovie();
        Mockito.verify(view).showErrorMessage();
    }

}