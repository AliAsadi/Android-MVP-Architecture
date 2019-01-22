package com.aliesaassadi.androidmvp.data;

import com.aliesaassadi.androidmvp.data.db.LogRepository;
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

    public LogRepository getLogRepository() {
        return LogRepository.getInstance();
    }

    public MovieService getMovieService() {
        return MovieService.getInstance();
    }

}
