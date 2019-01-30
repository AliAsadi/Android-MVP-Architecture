package com.aliesaassadi.androidmvp.data.movie.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aliesaassadi.androidmvp.data.movie.Movie;

import java.util.List;

 @Dao
public interface MovieDao {

     /**
      * Select all movies from the movies table.
      *
      * @return all movies.
      */
    @Query("SELECT * FROM movies")
    List<Movie> getMovies();

     /**
      * Insert all movies.
      */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<Movie> movies);

     /**
      * Delete all movies.
      */
     @Query("DELETE FROM movies")
     void deleteMovies();
}
