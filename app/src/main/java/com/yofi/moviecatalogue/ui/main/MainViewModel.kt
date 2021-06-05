package com.yofi.moviecatalogue.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yofi.moviecatalogue.data.source.remote.Repository
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow

class MainViewModel(private val repository: Repository): ViewModel() {
    fun getListMovie(): LiveData<List<ItemMovie>> =  repository.getMovie()

    fun getListTvShow(): LiveData<List<ItemTvShow>> =  repository.getTvShow()

    fun getSearchMovie(name: String): LiveData<List<ItemMovie>> = repository.getMovieByName(name)

    fun getSearchTvShow(name: String): LiveData<List<ItemTvShow>> = repository.getTvShowByName(name)
}