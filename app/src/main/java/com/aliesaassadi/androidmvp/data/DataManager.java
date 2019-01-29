package com.aliesaassadi.androidmvp.data;

import com.aliesaassadi.androidmvp.data.log.LogDataSource;
import com.aliesaassadi.androidmvp.data.log.LogRepository;
import com.aliesaassadi.androidmvp.data.movie.MovieDataSource;
import com.aliesaassadi.androidmvp.data.movie.MoviesRepository;
import com.aliesaassadi.androidmvp.data.movie.network.services.MovieApi;
import com.aliesaassadi.androidmvp.data.movie.network.services.MovieService;
import com.preference.PowerPreference;
import com.preference.Preference;

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

    public Preference getDefaultPreference() {
        return PowerPreference.getDefaultFile();
    }

    public Preference getUserPreference() { return PowerPreference.getFileByName("UserPreference"); }

    public LogRepository getLogRepository(LogDataSource logLocalDataSource) {
        return LogRepository.getInstance(logLocalDataSource);
    }
    public MovieApi getMovieApi() {
        return MovieService.getInstance().getMovieApi();
    }

    public MoviesRepository getMovieRepository(MovieDataSource movieRemote,
                                               MovieDataSource movieLocal) {
        return MoviesRepository.getInstance(movieRemote,movieLocal);
    }

}
