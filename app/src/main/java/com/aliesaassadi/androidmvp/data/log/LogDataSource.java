package com.aliesaassadi.androidmvp.data.log;

import com.aliesaassadi.androidmvp.data.log.db.entity.LogData;

public interface LogDataSource {
    void saveLog(LogData log);
}
