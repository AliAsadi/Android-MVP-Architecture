package com.aliesaassadi.androidmvp.data;

import com.aliesaassadi.androidmvp.data.movie.source.MoviesRepository;
import com.aliesaassadi.androidmvp.data.movie.source.local.MovieCacheDataSource;
import com.aliesaassadi.androidmvp.data.movie.source.local.MovieLocalDataSource;
import com.aliesaassadi.androidmvp.data.movie.source.remote.MovieRemoteDataSource;
import com.aliesaassadi.androidmvp.data.movie.source.remote.services.MovieApi;
import com.aliesaassadi.androidmvp.data.movie.source.remote.services.MovieService;
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

    public MovieApi getMovieApi() {
        return MovieService.getInstance().getMovieApi();
    }

    public MoviesRepository getMovieRepository(MovieRemoteDataSource movieRemote,
                                               MovieLocalDataSource movieLocal,
                                               MovieCacheDataSource movieCache) {
        return MoviesRepository.getInstance(movieRemote,movieLocal,movieCache);
    }

}
