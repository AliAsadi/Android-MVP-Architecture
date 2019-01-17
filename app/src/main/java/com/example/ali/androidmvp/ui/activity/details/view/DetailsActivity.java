package com.example.ali.androidmvp.ui.activity.details.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ali.androidmvp.R;
import com.example.ali.androidmvp.ui.activity.details.presenter.DetailsActivityPresenter;
import com.example.ali.androidmvp.ui.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public class DetailsActivity extends BaseActivity<DetailsActivityPresenter> implements DetailsActivityView {

    @BindView(R.id.image) AppCompatImageView mImage;
    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.desc) TextView mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        presenter.getIntentExtras(getIntent());
    }

    @NonNull
    @Override
    protected DetailsActivityPresenter createPresenter() {
        return new DetailsActivityPresenter(this);
    }

    @Override
    public void updateActivityView(String imageUrl, String title, String description) {
        mTitle.setText(title);
        mDesc.setText(description);
        Glide.with(getApplicationContext()).load(imageUrl).into(mImage);
    }
}
