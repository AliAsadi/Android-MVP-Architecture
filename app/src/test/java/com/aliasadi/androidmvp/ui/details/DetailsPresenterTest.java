package com.aliasadi.androidmvp.ui.details;

import android.content.Intent;

import com.aliasadi.androidmvp.data.movie.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ali Asadi on 30/01/2019.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailsPresenterTest {

    @Mock
    private DetailsView view;

    @Mock
    private Intent intent;

    private DetailsPresenter presenter;

    @Before
    public void setUp() {
        presenter = new DetailsPresenter(view, intent);
    }


    @Test
    public void showDataUnavailableMessage_WhenMovieDataNotExist() {
        when(intent.getParcelableExtra(DetailsPresenter.KEY_MOVIE)).thenReturn(null);
        presenter.onCreateView();
        verify(view).showDataUnavailableMessage();
    }

    @Test
    public void showMovieDetails_WhenGetMoveDataSuccess() {
        when(intent.getParcelableExtra(DetailsPresenter.KEY_MOVIE)).thenReturn(new Movie());
        presenter.onCreateView();
        verify(view, Mockito.only()).showMovieData((Movie) any());
    }

}
