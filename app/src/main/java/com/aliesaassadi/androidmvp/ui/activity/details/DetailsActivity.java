package com.aliesaassadi.androidmvp.ui.activity.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aliesaassadi.androidmvp.R;
import com.aliesaassadi.androidmvp.ui.activity.base.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView {

    @BindView(R.id.image) AppCompatImageView mImage;
    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.desc) TextView mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        presenter.getMovieData(getIntent());
    }

    @NonNull
    @Override
    protected DetailsPresenter createPresenter() {
        return new DetailsPresenter(this);
    }

    @Override
    public void showMovieDetails(String imageUrl, String title, String description) {
        mTitle.setText(title);
        mDesc.setText(description);
        Picasso.get().load(imageUrl).into(mImage);
    }

    @Override
    public void showDataUnavailableMessage() {
        Toast.makeText(this, "Data Unavailable", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}
