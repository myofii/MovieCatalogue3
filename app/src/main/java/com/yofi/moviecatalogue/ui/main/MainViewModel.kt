package com.yofi.moviecatalogue.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.yofi.moviecatalogue.data.Repository
import com.yofi.moviecatalogue.data.source.local.entities.MovieEntity
import com.yofi.moviecatalogue.data.source.local.entities.TvShowEntity
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import com.yofi.moviecatalogue.vo.Resource

class MainViewModel(private val repository: Repository): ViewModel() {
    fun getListMovie(): LiveData<Resource<PagedList<MovieEntity>>> =  repository.getMovie()

    fun getListTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> =  repository.getTvShow()

    fun getSearchMovie(name: String): LiveData<Resource<PagedList<MovieEntity>>> = repository.getMovieByName(name)

    fun getSearchTvShow(name: String): LiveData<Resource<PagedList<TvShowEntity>>> = repository.getTvShowByName(name)
}