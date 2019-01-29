package com.aliesaassadi.androidmvp.data.movie.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.aliesaassadi.androidmvp.App;
import com.aliesaassadi.androidmvp.data.movie.Movie;
import com.aliesaassadi.androidmvp.data.movie.db.dao.MovieDao;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();

    private static MovieDatabase sInstance;

    public static MovieDatabase getInstance() {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(App.getInstance(), MovieDatabase.class, "Movie.db").build();
        }
        return sInstance;
    }
}
