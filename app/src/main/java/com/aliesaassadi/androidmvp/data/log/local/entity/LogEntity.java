package com.aliesaassadi.androidmvp.data.log.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ali Esa Assadi on 07/03/2018.
 */

@Entity(tableName = "logs")
public class LogEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "log")
    private String log;

    @ColumnInfo(name = "class")
    private String className;

    @ColumnInfo(name = "date")
    private String date;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
