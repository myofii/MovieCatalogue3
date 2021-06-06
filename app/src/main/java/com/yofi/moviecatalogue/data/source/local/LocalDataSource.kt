package com.yofi.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity
import com.yofi.moviecatalogue.data.source.local.room.MovieCatalogueDao

class LocalDataSource private constructor(private val movieCatalogueDao: MovieCatalogueDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieCatalogueDao: MovieCatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieCatalogueDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = movieCatalogueDao.getMovie()

    fun getAllTvshow(): DataSource.Factory<Int, TvShowEntity> = movieCatalogueDao.getTvshow()

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> = movieCatalogueDao.getFavMovies()

    fun getFavTvshows(): DataSource.Factory<Int, TvShowEntity> = movieCatalogueDao.getFavTvshows()

    fun getMovieById(movieId: Int): LiveData<MovieEntity> = movieCatalogueDao.getMovieById(movieId)

    fun getTvshowById(tvshowId: Int): LiveData<TvShowEntity> = movieCatalogueDao.getTvshowById(tvshowId)

    fun insertMovie(movies: MovieEntity) = movieCatalogueDao.insertMovie(movies)

    fun insertTvshows(tvshows: TvShowEntity) = movieCatalogueDao.insertTvshow(tvshows)

    fun insertListMovie(listMovie: List<MovieEntity>) = movieCatalogueDao.insertListMovie(listMovie)

    fun insertListTvShow(listTvShow: List<TvShowEntity>) = movieCatalogueDao.insertListTvshow(listTvShow)

    fun getMovieByName(q: String): DataSource.Factory<Int, MovieEntity>  = movieCatalogueDao.getMovieByName(q)

    fun getTvShowByName(q: String): DataSource.Factory<Int, TvShowEntity>  = movieCatalogueDao.getTvShowByName(q)

    fun setFavMovie(movie: MovieEntity, newState: Boolean): Int {
        movie.favorite = newState
        movieCatalogueDao.updateMovie(movie)
        return movie.id
    }

    fun setFavTvshow(tvshow: TvShowEntity, newState: Boolean): Int {
        tvshow.favorite = newState
        movieCatalogueDao.updateTvshow(tvshow)
        return tvshow.id
    }
}