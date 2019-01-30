package com.aliesaassadi.androidmvp.data.log.source.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.aliesaassadi.androidmvp.App;
import com.aliesaassadi.androidmvp.data.log.source.local.dao.LogDao;
import com.aliesaassadi.androidmvp.data.log.Log;

/**
 * Created by Ali Esa Assadi on 07/03/2018.
 */

@Database(entities = {Log.class}, version = 2 , exportSchema = false)
public abstract class LogDatabase extends RoomDatabase {

    public abstract LogDao logDao();

    private static LogDatabase sInstance;

    public static LogDatabase getInstance() {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(App.getInstance(), LogDatabase.class, "Log.db").build();
        }
        return sInstance;
    }
}
