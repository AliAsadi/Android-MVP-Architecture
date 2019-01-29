package com.aliesaassadi.androidmvp.data.log.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ali Esa Assadi on 07/03/2018.
 */

@Entity
public class LogData {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "Log")
    private String log;

    @ColumnInfo(name = "Class")
    private String className;

    @ColumnInfo(name = "Date")
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
    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }
    public String getLog() {
        return log;
    }
    public void setLog(String log) {
        this.log = log;
    }
}
