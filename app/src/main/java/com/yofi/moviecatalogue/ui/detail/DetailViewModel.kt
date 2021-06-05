package com.yofi.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yofi.moviecatalogue.data.DataEntity
import com.yofi.moviecatalogue.data.source.local.Dummy
import com.yofi.moviecatalogue.data.source.remote.Repository
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow

class DetailViewModel(private val repository: Repository): ViewModel() {

    fun getDetailMovieById(id: Int): LiveData<ItemMovie> = repository.getMovieById(id)
    fun getDetailTvShowById(id: Int): LiveData<ItemTvShow> = repository.getTvShowById(id)

}
