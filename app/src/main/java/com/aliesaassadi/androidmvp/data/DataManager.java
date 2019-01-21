package com.aliesaassadi.androidmvp.data;

import com.aliesaassadi.androidmvp.App;
import com.aliesaassadi.androidmvp.data.db.database.LogDatabase;
import com.aliesaassadi.androidmvp.data.network.services.MovieService;
import com.aliesaassadi.androidmvp.data.prefs.Prefs;

/**
 * Created by Ali Esa Assadi on 26/03/2018.
 */

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public Prefs getPrefs() {
        return Prefs.getInstance();
    }

    public LogDatabase getLogDatabse() {
        return LogDatabase.getInstance(App.getInstance());
    }

    public MovieService getMovieService() {
        return new MovieService();
    }

}
