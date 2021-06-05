package com.yofi.moviecatalogue.data

import com.yofi.moviecatalogue.data.source.remote.RemoteDataSource
import com.yofi.moviecatalogue.data.source.remote.Repository

object Injection {
    fun provideRepository(): Repository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return Repository.getInstance(remoteDataSource)
    }
}