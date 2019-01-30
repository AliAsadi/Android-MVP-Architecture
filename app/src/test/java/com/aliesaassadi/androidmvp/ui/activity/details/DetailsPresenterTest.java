package com.aliesaassadi.androidmvp.ui.activity.details;

import android.content.Intent;
import android.os.Bundle;

import com.aliesaassadi.androidmvp.data.movie.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by Ali Esa Assadi on 30/01/2019.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailsPresenterTest {

    @Mock
    private DetailsView view;

    private DetailsPresenter presenter;

    @Before
    public void setUp() {
        presenter = new DetailsPresenter(view);
    }

    @Test
    public void showErrorMessage_WhenSomethingWentWrong() {
        presenter.getMovieData(null);
        Mockito.verify(view).showErrorMessage();
    }

    @Test
    public void showErrorMessage_WhenExtraIsNull() {
        Intent intent = Mockito.mock(Intent.class);
        Mockito.when(intent.getExtras()).thenReturn(null);

        presenter.getMovieData(intent);
        Mockito.verify(view).showErrorMessage();
    }

    @Test
    public void showDataUnavailableMessage_WhenMovieDataNotExist() {
        Intent intent = Mockito.mock(Intent.class);
        Bundle bundle = Mockito.mock(Bundle.class);

        Mockito.when(intent.getExtras()).thenReturn(bundle);
        Mockito.when(bundle.getParcelable(anyString())).thenReturn(null);

        presenter.getMovieData(intent);
        Mockito.verify(view).showDataUnavailableMessage();
    }

    @Test
    public void showMovieDetails_WhenGetMoveDataSuccess() {
        Intent intent = Mockito.mock(Intent.class);
        Bundle bundle = Mockito.mock(Bundle.class);

        Mockito.when(intent.getExtras()).thenReturn(bundle);

        Movie movie = new Movie("bla bla", "sad.jpg", "title");
        Mockito.when(bundle.getParcelable(anyString())).thenReturn(movie);

        presenter.getMovieData(intent);
        Mockito.verify(view,Mockito.only()).showMovieDetails(anyString(), anyString(), anyString());
    }

}
