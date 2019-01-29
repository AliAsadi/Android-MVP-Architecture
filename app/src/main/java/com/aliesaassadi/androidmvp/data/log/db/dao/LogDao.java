package com.aliesaassadi.androidmvp.data.log.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.aliesaassadi.androidmvp.data.log.db.entity.LogEntity;

import java.util.List;

/**
 * Created by Ali Esa Assadi on 07/03/2018.
 */

@Dao
public interface LogDao {

    @Query("SELECT * FROM LogEntity")
    List<LogEntity> getLogs();

    @Insert
    void insertLog(LogEntity log);
}
