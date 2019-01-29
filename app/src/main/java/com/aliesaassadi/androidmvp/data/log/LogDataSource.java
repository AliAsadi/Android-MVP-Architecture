package com.aliesaassadi.androidmvp.data.log;

import com.aliesaassadi.androidmvp.data.log.db.entity.LogEntity;

public interface LogDataSource {
    void saveLog(LogEntity log);
}
