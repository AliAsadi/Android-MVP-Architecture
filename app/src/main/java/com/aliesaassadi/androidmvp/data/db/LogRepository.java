package com.aliesaassadi.androidmvp.data.db;

import com.aliesaassadi.androidmvp.data.db.dao.LogDAO;
import com.aliesaassadi.androidmvp.data.db.database.LogDatabase;
import com.aliesaassadi.androidmvp.data.db.entity.LogClass;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Ali Esa Assadi on 21/01/2019.
 */
public class LogRepository {

    private Executor executor;
    private LogDAO logDAO;

    private static LogRepository instance;

    private LogRepository(Executor executor, LogDAO logDAO) {
        this.executor = executor;
        this.logDAO = logDAO;
    }

    public static LogRepository getInstance() {
        if (instance == null) {
            instance = new LogRepository(Executors.newSingleThreadExecutor(), LogDatabase.getInstance().logDao());
        }
        return instance;
    }

    public void saveLog(final LogClass log) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                logDAO.insertLog(log);
            }
        };
        executor.execute(runnable);
    }
}
