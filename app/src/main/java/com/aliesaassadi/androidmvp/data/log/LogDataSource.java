package com.aliesaassadi.androidmvp.data.log;

import com.aliesaassadi.androidmvp.data.log.local.entity.LogEntity;

public interface LogDataSource {
    void saveLog(LogEntity log);
}
