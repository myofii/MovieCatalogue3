package com.yofi.moviecatalogue.data.source.remote

import com.yofi.moviecatalogue.data.EspressoIdlingResource
import com.yofi.moviecatalogue.data.source.api.ApiConf
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import retrofit2.await

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getMovies(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        ApiConf.getApiService().getMoviesApi().await().results.let { getListMovies ->
            callback.onAllMovieReceived(
                getListMovies
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShows(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiConf.getApiService().getTvShowsApi().await().results.let { getListTvShows ->
            callback.onAllTvShowReceived(
                getListTvShows
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieByIdCallback) {
        EspressoIdlingResource.increment()
        ApiConf.getApiService().getMoviesById(movieId).await().let { movieById ->
            callback.onMovieDetailReceived(
                movieById
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowDetail(tvShowsId: Int, callback: LoadTvShowsByIdCallback) {
        EspressoIdlingResource.increment()
        ApiConf.getApiService().getTvShowById(tvShowsId).await().let { tvShowsById ->
            callback.onTvShowsDetailReceived(
                tvShowsById
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMoviesByName(q: String, callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        ApiConf.getApiService().getMoviesByName(q).await().results.let { getListMovies ->
            callback.onAllMovieReceived(
                getListMovies
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowsByName(q: String, callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiConf.getApiService().getTvShowByName(q).await().results.let { getListTvShows ->
            callback.onAllTvShowReceived(
                getListTvShows
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movie: List<ItemMovie>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShow: List<ItemTvShow>)
    }

    interface LoadMovieByIdCallback {
        fun onMovieDetailReceived(movie: ItemMovie)
    }

    interface LoadTvShowsByIdCallback {
        fun onTvShowsDetailReceived(tvShow: ItemTvShow)
    }
}