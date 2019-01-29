package com.aliesaassadi.androidmvp.data.movie.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.aliesaassadi.androidmvp.data.movie.db.dao.MovieDao;
import com.aliesaassadi.androidmvp.data.movie.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
