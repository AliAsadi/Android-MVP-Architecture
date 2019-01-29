package com.aliesaassadi.androidmvp.data.log;

import com.aliesaassadi.androidmvp.data.log.db.entity.LogEntity;

/**
 * Created by Ali Esa Assadi on 21/01/2019.
 */
public class LogRepository implements LogDataSource{

    private final LogDataSource logLocalDataSource;

    private static LogRepository instance;

    private LogRepository(LogDataSource logLocalDataSource) {
        this.logLocalDataSource = logLocalDataSource;
    }

    public static LogRepository getInstance(LogDataSource logLocalDataSource) {
        if (instance == null) {
            instance = new LogRepository(logLocalDataSource);
        }
        return instance;
    }

    @Override
    public void saveLog(LogEntity log) {
        logLocalDataSource.saveLog(log);
    }
}
