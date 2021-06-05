package com.yofi.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FakeRepository (private val RemoteDataSource: RemoteDataSource) :
    CatalogueDataSource {

    private val _isLoading = MutableLiveData<Boolean>()

    companion object {
        @Volatile
        private var instance: FakeRepository? = null


        fun getInstance(remoteData: RemoteDataSource): FakeRepository =
            instance ?: synchronized(this) {
                instance ?: FakeRepository(remoteData).apply { instance = this }
            }

    }

    override fun getMovie(): LiveData<List<ItemMovie>> {
        val listMoviesOutput = MutableLiveData<List<ItemMovie>>()
        _isLoading.value = true
        CoroutineScope(IO).launch {
            RemoteDataSource.getMovies(object :
                RemoteDataSource.LoadMovieCallback {
                override fun onAllMovieReceived(movie: List<ItemMovie>) {
                    val listMovies = ArrayList<ItemMovie>()
                    for (response in movie) {
                        val movies = ItemMovie(
                            response.id,
                            response.title,
                            response.posterPath,
                            response.releaseDate,
                            response.voteAverage,
                            response.overview
                        )
                        listMovies.add(movies)
                    }
                    listMoviesOutput.postValue(listMovies)
                    _isLoading.postValue(false)
                }
            })
        }
        return listMoviesOutput
    }

    override fun getTvShow(): LiveData<List<ItemTvShow>> {
        val listTvShowOutput = MutableLiveData<List<ItemTvShow>>()
        CoroutineScope(IO).launch {
            RemoteDataSource.getTvShows(object :
                RemoteDataSource.LoadTvShowCallback {
                override fun onAllTvShowReceived(tvShow: List<ItemTvShow>) {
                    val listTvShow = ArrayList<ItemTvShow>()
                    for (response in tvShow) {
                        val tvShows = ItemTvShow(
                            response.id,
                            response.originalName,
                            response.posterPath,
                            response.firstAirDate,
                            response.voteAverage,
                            response.overview
                        )
                        listTvShow.add(tvShows)
                        _isLoading.postValue(false)
                    }
                    listTvShowOutput.postValue(listTvShow)
                }
            })
        }
        return listTvShowOutput
    }

    override fun getTvShowById(tvShowId: Int): LiveData<ItemTvShow> {
        val tvShowDetail = MutableLiveData<ItemTvShow>()
        CoroutineScope(IO).launch {
            RemoteDataSource.getTvShowDetail(
                tvShowId,
                object : RemoteDataSource.LoadTvShowsByIdCallback {
                    override fun onTvShowsDetailReceived(tvShow: ItemTvShow) {
                        val tvShowsDetail = ItemTvShow(
                            tvShow.id,
                            tvShow.originalName,
                            tvShow.posterPath,
                            tvShow.firstAirDate,
                            tvShow.voteAverage,
                            tvShow.overview
                        )
                        tvShowDetail.postValue(tvShowsDetail)
                        _isLoading.postValue(false)
                    }
                })
        }
        return tvShowDetail
    }

    override fun getMovieById(movieId: Int): LiveData<ItemMovie> {
        val movieDetail = MutableLiveData<ItemMovie>()
        CoroutineScope(IO).launch {
            RemoteDataSource.getMovieDetail(
                movieId,
                object : RemoteDataSource.LoadMovieByIdCallback {
                    override fun onMovieDetailReceived(movie: ItemMovie) {
                        val moviesDetail = ItemMovie(
                            movie.id,
                            movie.title,
                            movie.posterPath,
                            movie.releaseDate,
                            movie.voteAverage,
                            movie.overview
                        )
                        movieDetail.postValue(moviesDetail)
                        _isLoading.postValue(false)
                    }
                })
        }
        return movieDetail
    }
}
