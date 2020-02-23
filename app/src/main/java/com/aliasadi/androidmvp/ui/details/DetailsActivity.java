package com.aliasadi.androidmvp.ui.details;

import android.content.Context;
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


/**
 * Created by Ali Asadi on 12/03/2018.
 */
public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    @BindView(R.id.image) AppCompatImageView image;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.desc) TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        presenter.onAttach();
    }

    @Override
    public void showMovieData(Movie movie) {
        title.setText(movie.getTitle());
        desc.setText(movie.getDescription());
        Picasso.get().load(movie.getImage()).into(image);
    }

    @Override
    public void showDataUnavailableMessage() {
        Toast.makeText(this, "Data Unavailable", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    protected DetailsPresenter createPresenter() {
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        return new DetailsPresenter(this, movie);
    }

    public static void start(Context context,Movie movie) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        context.startActivity(intent);
    }
}
