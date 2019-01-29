package com.aliesaassadi.androidmvp.data.log.db;

import com.aliesaassadi.androidmvp.data.log.db.dao.LogDao;
import com.aliesaassadi.androidmvp.data.log.db.entity.LogEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LogLocalDataSource {
    private Executor executor;
    private LogDao logDao;

    private static LogLocalDataSource instance;

    private LogLocalDataSource(Executor executor, LogDao logDao) {
        this.executor = executor;
        this.logDao = logDao;
    }

    public static LogLocalDataSource getInstance(LogDao logDao) {
        if (instance == null) {
            instance = new LogLocalDataSource(Executors.newSingleThreadExecutor(), logDao);
        }
        return instance;
    }

    public void saveLog(final LogEntity log) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                logDao.insertLog(log);
            }
        };
        executor.execute(runnable);
    }
}
