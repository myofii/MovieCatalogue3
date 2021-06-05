package com.yofi.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.yofi.moviecatalogue.data.Repository
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity

class FavoriteViewModel(private val repository: Repository): ViewModel() {
    fun getFavMovie(): LiveData<PagedList<MovieEntity>> =  repository.getFavMovie()

    fun getFavTvShow(): LiveData<PagedList<TvShowEntity>> =  repository.getFavTvshow()
}