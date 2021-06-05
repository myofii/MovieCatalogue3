package com.yofi.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity

@Dao
interface MovieCatalogueDao {
    @Query("SELECT * FROM movie")
    fun getMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvShow")
    fun getTvshow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tvShow WHERE id = :id")
    fun getTvshowById(id: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM movie WHERE original_title LIKE :q")
    fun getMovieByName(q: String): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvShow WHERE original_name LIKE :q")
    fun getTvShowByName(q: String): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListTvshow(tvshow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvshow(tvshow: TvShowEntity)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvshow(tvshow: TvShowEntity)

    @Query("SELECT * FROM movie WHERE favorite = 1")
    fun getFavMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvShow WHERE favorite = 1")
    fun getFavTvshows(): DataSource.Factory<Int, TvShowEntity>
}