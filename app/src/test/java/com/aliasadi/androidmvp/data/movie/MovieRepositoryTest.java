package com.aliasadi.androidmvp.data.movie;

import com.aliasadi.androidmvp.data.movie.source.MovieDataSource;
import com.aliasadi.androidmvp.data.movie.source.MoviesRepository;
import com.aliasadi.androidmvp.data.movie.source.local.MovieCacheDataSource;
import com.aliasadi.androidmvp.data.movie.source.local.MovieLocalDataSource;
import com.aliasadi.androidmvp.data.movie.source.remote.MovieRemoteDataSource;

import org.junit.After;
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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Ali Asadi on 01/02/2019.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieRepositoryTest {

    @Mock
    MovieLocalDataSource movieLocal;

    @Mock
    MovieCacheDataSource movieCache;

    @Mock
    MovieRemoteDataSource movieRemote;


    @Captor
    ArgumentCaptor<MovieDataSource.LoadMoviesCallback> loadMoviesCallbackCapture;

    @Mock
    MovieDataSource.LoadMoviesCallback loadMoviesCallback;

    private static List<Movie> MOVIES = Arrays.asList(new Movie(), new Movie());

    private MoviesRepository moviesRepository;

    @Before
    public void setUp() {
        moviesRepository = MoviesRepository.getInstance(movieRemote, movieLocal, movieCache);
    }

    @After
    public void destroy() {
        moviesRepository.destroyInstance();
    }

    @Test
    public void getMovies_checkCacheDataSourceEveryRequest() {
        moviesRepository.getMovies(loadMoviesCallback);
        verify(movieCache).getMovies((MovieDataSource.LoadMoviesCallback) Mockito.any());
    }

    @Test
    public void getMovies_requestDataFromCacheDataSource() {
        moviesRepository.getMovies(loadMoviesCallback);

        setMoviesAvailable(movieCache);

        verify(loadMoviesCallback).onMoviesLoaded(MOVIES);
    }

    @Test
    public void getMovies_requestDataFromCacheAfterFirstApiCall() {

        moviesRepository.getMovies(loadMoviesCallback);

        setMoviesNotAvailable(movieCache);
        setMoviesNotAvailable(movieLocal);
        setMoviesAvailable(movieRemote);

        verify(movieCache).saveMovies(Mockito.<Movie>anyList());

        moviesRepository.getMovies(loadMoviesCallback);

        verify(movieCache, times(2)).getMovies((MovieDataSource.LoadMoviesCallback) Mockito.any());
        verify(movieRemote, times(1)).getMovies((MovieDataSource.LoadMoviesCallback) Mockito.any());
        verify(movieLocal, times(1)).getMovies((MovieDataSource.LoadMoviesCallback) Mockito.any());
    }

    @Test
    public void getMovies_verifyThatDataSavedInCacheAfterApiCall() {
        moviesRepository.getMovies(loadMoviesCallback);

        setMoviesNotAvailable(movieCache);
        setMoviesNotAvailable(movieLocal);
        setMoviesAvailable(movieRemote);

        verify(movieCache).saveMovies(Mockito.<Movie>anyList());
    }

    @Test
    public void getMovies_verifyThatDataSavedInCacheAfterLocalCall() {
        moviesRepository.getMovies(loadMoviesCallback);

        setMoviesNotAvailable(movieCache);
        setMoviesAvailable(movieLocal);

        verify(movieCache).saveMovies(Mockito.<Movie>anyList());
    }

    @Test
    public void getMovies_requestDataFromLocalDataSource() {

        moviesRepository.getMovies(loadMoviesCallback);

        setMoviesNotAvailable(movieCache);
        setMoviesAvailable(movieLocal);

        verify(loadMoviesCallback).onMoviesLoaded(MOVIES);
    }

    @Test
    public void saveMovies_verifyThatMoviesSavedInLocalAfterFirstApiCall() {
        moviesRepository.getMovies(loadMoviesCallback);

        setMoviesNotAvailable(movieCache);
        setMoviesNotAvailable(movieLocal);
        setMoviesAvailable(movieRemote);

        verify(movieLocal).saveMovies(Mockito.<Movie>anyList());
    }

    @Test
    public void saveMovies_verifyThatMoviesSavedInDatabaseAndMemoryCacheAfterFirstApiCall() {
        moviesRepository.getMovies(loadMoviesCallback);

        setMoviesNotAvailable(movieCache);
        setMoviesNotAvailable(movieLocal);
        setMoviesAvailable(movieRemote);

        verify(movieLocal).saveMovies(Mockito.<Movie>anyList());
        verify(movieLocal).saveMovies(Mockito.<Movie>anyList());
    }

    @Test
    public void getMovies_requestDataFromRemoteDataSource() {
        moviesRepository.getMovies(loadMoviesCallback);

        setMoviesNotAvailable(movieCache);
        setMoviesNotAvailable(movieLocal);
        setMoviesAvailable(movieRemote);

        verify(loadMoviesCallback).onMoviesLoaded(MOVIES);
    }

    @Test
    public void getMovies_requestDataFromRemoteDataSourceNotAvailable() {
        moviesRepository.getMovies(loadMoviesCallback);

        setMoviesNotAvailable(movieCache);
        setMoviesNotAvailable(movieLocal);
        setMoviesNotAvailable(movieRemote);

        verify(loadMoviesCallback).onDataNotAvailable();
    }

    @Test
    public void getMovies_requestDataFromRemoteDataSourceReturnError() {
        moviesRepository.getMovies(loadMoviesCallback);

        setMoviesNotAvailable(movieCache);
        setMoviesNotAvailable(movieLocal);
        setMoviesErrorResponse(movieRemote);

        verify(loadMoviesCallback).onError();
    }

    private void setMoviesErrorResponse(MovieDataSource dataSource) {
        verify(dataSource).getMovies(loadMoviesCallbackCapture.capture());
        loadMoviesCallbackCapture.getValue().onError();
    }

    private void setMoviesNotAvailable(MovieDataSource dataSource) {
        verify(dataSource).getMovies(loadMoviesCallbackCapture.capture());
        loadMoviesCallbackCapture.getValue().onDataNotAvailable();
    }

    private void setMoviesAvailable(MovieDataSource dataSource) {
        verify(dataSource).getMovies(loadMoviesCallbackCapture.capture());
        loadMoviesCallbackCapture.getValue().onMoviesLoaded(MOVIES);
    }

}
