package com.aliasadi.androidmvp.data;

import com.aliasadi.androidmvp.data.movie.source.MoviesRepository;
import com.aliasadi.androidmvp.data.movie.source.local.MovieCacheDataSource;
import com.aliasadi.androidmvp.data.movie.source.local.MovieLocalDataSource;
import com.aliasadi.androidmvp.data.movie.source.local.dao.MovieDao;
import com.aliasadi.androidmvp.data.movie.source.local.database.MovieDatabase;
import com.aliasadi.androidmvp.data.movie.source.remote.MovieRemoteDataSource;
import com.aliasadi.androidmvp.data.movie.source.remote.services.MovieApi;
import com.aliasadi.androidmvp.data.movie.source.remote.services.MovieService;
import com.preference.PowerPreference;
import com.preference.Preference;

/**
 * Created by Ali Asadi on 26/03/2018.
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

    public MoviesRepository getMovieRepository() {

        MovieApi movieApi = MovieService.getInstance().getMovieApi();
        MovieRemoteDataSource movieRemote = MovieRemoteDataSource.getInstance(movieApi);

        MovieDao movieDao = MovieDatabase.getInstance().movieDao();
        MovieLocalDataSource movieLocal = MovieLocalDataSource.getInstance(movieDao);

        MovieCacheDataSource movieCache = MovieCacheDataSource.getsInstance();

        return MoviesRepository.getInstance(movieRemote,movieLocal,movieCache);
    }

}
