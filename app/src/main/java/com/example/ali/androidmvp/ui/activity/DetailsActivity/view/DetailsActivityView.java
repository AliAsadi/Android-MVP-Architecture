package com.example.ali.androidmvp.ui.activity.DetailsActivity.view;

import com.example.ali.androidmvp.data.network.model.MovieResponse;
import com.example.ali.androidmvp.ui.activity.base.BaseView;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public interface DetailsActivityView extends BaseView {
    void updateActivityView(String imageUrl, String title, String description);
}
