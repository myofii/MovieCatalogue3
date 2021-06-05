package com.yofi.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yofi.moviecatalogue.data.Repository
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import com.yofi.moviecatalogue.vo.Resource

class DetailViewModel(private val repository: Repository): ViewModel() {

    val tvShowId = MutableLiveData<Int>()
    val movieId = MutableLiveData<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun getDetailMovieById(): LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { mmovieId ->
        repository.getMovieById(mmovieId)
    }

    fun getDetailTvShowById(): LiveData<Resource<TvShowEntity>>  = Transformations.switchMap(tvShowId) { ttvShowId ->
        repository.getTvShowById(ttvShowId)
    }

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.favorite
        repository.setFavMovie(movieEntity, newState)
    }

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.favorite
        repository.setFavTvshow(tvShowEntity, newState)
    }

}
