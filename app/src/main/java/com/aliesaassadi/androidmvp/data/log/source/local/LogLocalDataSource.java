package com.aliesaassadi.androidmvp.data.log.source.local;

import com.aliesaassadi.androidmvp.data.log.Log;
import com.aliesaassadi.androidmvp.data.log.source.LogDataSource;
import com.aliesaassadi.androidmvp.data.log.source.local.dao.LogDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LogLocalDataSource implements LogDataSource {

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


    @Override
    public void saveLog(final Log log) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                logDao.saveLog(log);
            }
        };
        executor.execute(runnable);
    }
}
