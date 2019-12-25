package com.aliasadi.androidmvp.ui.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aliasadi.androidmvp.R;
import com.aliasadi.androidmvp.data.movie.Movie;
import com.aliasadi.androidmvp.ui.base.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aliasadi.androidmvp.ui.details.DetailsPresenter.KEY_MOVIE;

/**
 * Created by Ali Asadi on 12/03/2018.
 */
public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView {

    @BindView(R.id.image)
    AppCompatImageView mImage;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.desc)
    TextView mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        presenter.onCreateView();
    }

    public static Intent newIntent(Activity activity, Movie movie) {
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putExtra(KEY_MOVIE, movie);
        return intent;
    }

    @NonNull
    @Override
    protected DetailsPresenter createPresenter() {
        return new DetailsPresenter(this, getIntent());
    }

    @Override
    public void showMovieData(Movie movie) {
        mTitle.setText(movie.getTitle());
        mDesc.setText(movie.getDescription());
        Picasso.get().load(movie.getImage()).into(mImage);
    }

    @Override
    public void showDataUnavailableMessage() {
        Toast.makeText(this, "Data Unavailable", Toast.LENGTH_SHORT).show();
    }
}
