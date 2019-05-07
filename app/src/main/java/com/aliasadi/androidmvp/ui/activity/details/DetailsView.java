package com.aliasadi.androidmvp.ui.activity.details;

import com.aliasadi.androidmvp.data.movie.Movie;
import com.aliasadi.androidmvp.ui.activity.base.BaseView;

/**
 * Created by Ali Asadi on 12/03/2018.
 */
public interface DetailsView extends BaseView {

    void showMovieDetails(Movie movie);

    void showDataUnavailableMessage();

}
