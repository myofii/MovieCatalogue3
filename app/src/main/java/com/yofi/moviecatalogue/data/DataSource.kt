package com.yofi.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import com.yofi.moviecatalogue.vo.Resource

interface DataSource {
    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getTvShowById(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getMovieByName(q: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvShowByName(q: String): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavTvshow(): LiveData<PagedList<TvShowEntity>>

    fun setFavMovie(movie: MovieEntity, state: Boolean): Int

    fun setFavTvshow(tvShow: TvShowEntity, state: Boolean): Int
}