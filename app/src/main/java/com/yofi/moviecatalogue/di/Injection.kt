package com.yofi.moviecatalogue.di

import android.content.Context
import com.yofi.moviecatalogue.data.Repository
import com.yofi.moviecatalogue.data.source.local.LocalDataSource
import com.yofi.moviecatalogue.data.source.local.room.MovieCatalogueDatabase
import com.yofi.moviecatalogue.data.source.remote.RemoteDataSource
import com.yofi.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = MovieCatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieCatalogueDao())
        val appExecutors = AppExecutors()

        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}