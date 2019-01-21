package com.aliesaassadi.androidmvp.ui.activity.details;

import com.aliesaassadi.androidmvp.ui.activity.base.BaseView;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public interface DetailsView extends BaseView {
    void updateActivityView(String imageUrl, String title, String description);
}
