package com.aliesaassadi.androidmvp.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.aliesaassadi.androidmvp.App;
import com.aliesaassadi.androidmvp.utils.AppConstants;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public class Prefs {

    private static Prefs sInstance;
    private SharedPreferences mPrefs;

    private Prefs() {
        mPrefs = App.getInstance().getSharedPreferences(AppConstants.SP_NAME, Context.MODE_PRIVATE);
    }

    private static Prefs init() {
        synchronized (Prefs.class) {
            if (sInstance == null) {
                sInstance = new Prefs();
                return sInstance;
            }
        }
        return sInstance;
    }

    public static synchronized Prefs getInstance() {
        if (sInstance == null) {
            sInstance = new Prefs();
        }
        return sInstance;
    }

    public boolean contains(String key) {
        return mPrefs.contains(key);
    }

    public void putBoolean(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    public void putString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    public String getStringOrEmpty(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

    public void putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPrefs.getBoolean(key, defValue);
    }

    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long defValue) {
        return mPrefs.getLong(key, defValue);
    }

    public void putLong(String key, long value) {
        mPrefs.edit().putLong(key, value).apply();
    }
}