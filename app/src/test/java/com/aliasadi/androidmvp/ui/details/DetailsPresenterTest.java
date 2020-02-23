package com.aliasadi.androidmvp.ui.details;

import com.aliasadi.androidmvp.data.movie.Movie;

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

    @Test
    public void showDataUnavailableMessage_WhenMovieDataNotExist() {
        presenter = new DetailsPresenter(view, null);
        presenter.onAttach();
        verify(view).showDataUnavailableMessage();
    }

    @Test
    public void showMovieDetails_WhenGetMoveDataSuccess() {
        presenter = new DetailsPresenter(view, new Movie());
        presenter.onAttach();
        verify(view, Mockito.only()).showMovieData((Movie) any());
    }

}
