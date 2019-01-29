package com.aliesaassadi.androidmvp.data.log.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.aliesaassadi.androidmvp.data.log.db.dao.LogDao;
import com.aliesaassadi.androidmvp.data.log.db.entity.LogEntity;

/**
 * Created by Ali Esa Assadi on 07/03/2018.
 */

@Database(entities = {LogEntity.class}, version = 2 , exportSchema = false)
public abstract class LogDatabase extends RoomDatabase {
    public abstract LogDao logDao();
}
