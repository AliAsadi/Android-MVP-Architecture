package com.aliasadi.androidmvp.ui.details;

import com.aliasadi.androidmvp.data.movie.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by Ali Asadi on 30/01/2019.
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
    public void showDataUnavailableMessage_WhenMovieDataNotExist() {
        presenter.onMovieReceived(null);
        verify(view).showDataUnavailableMessage();
    }

    @Test
    public void showMovieDetails_WhenGetMoveDataSuccess() {
        presenter.onMovieReceived(new Movie());
        verify(view, Mockito.only()).showMovieDetails((Movie) any());
    }

}
