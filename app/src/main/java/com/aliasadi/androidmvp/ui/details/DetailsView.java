package com.aliasadi.androidmvp.ui.details;

import com.aliasadi.androidmvp.data.movie.Movie;
import com.aliasadi.androidmvp.ui.base.BaseView;

/**
 * Created by Ali Asadi on 12/03/2018.
 */
public interface DetailsView extends BaseView {

    void showMovieData(Movie movie);

    void showDataUnavailableMessage();

}
