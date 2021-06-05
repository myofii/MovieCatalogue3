package com.yofi.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yofi.moviecatalogue.utils.EspressoIdlingResource
import com.yofi.moviecatalogue.data.source.api.ApiConf
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import com.yofi.moviecatalogue.data.source.response.MovieResponse
import com.yofi.moviecatalogue.data.source.response.TvShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun getMovies(): LiveData<ApiResponse<List<ItemMovie>>> {
        EspressoIdlingResource.increment()
        val listMovie = MutableLiveData<ApiResponse<List<ItemMovie>>>()
        val client = ApiConf.getApiService().getMoviesApi()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    listMovie.value =
                        ApiResponse.success(response.body()?.results as List<ItemMovie>)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e("getMovie", "Not Success: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("getMovie", "onFailure: ${t.message.toString()}")
            }
        })
        return listMovie
    }

    fun getMovieById(id: Int): LiveData<ApiResponse<ItemMovie>> {
        EspressoIdlingResource.increment()
        val movie = MutableLiveData<ApiResponse<ItemMovie>>()
        val client = ApiConf.getApiService().getMoviesById(id)
        client.enqueue(object : Callback<ItemMovie> {
            override fun onResponse(
                call: Call<ItemMovie>,
                response: Response<ItemMovie>
            ) {
                if (response.isSuccessful) {
                    movie.value = ApiResponse.success(response.body() as ItemMovie)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e("getMovieById", "Not Success: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ItemMovie>, t: Throwable) {
                Log.e("getMovieById", "onFailure: ${t.message.toString()}")
            }
        })
        return movie
    }

    fun getTvShows(): LiveData<ApiResponse<List<ItemTvShow>>> {
        EspressoIdlingResource.increment()
        val listTvShow = MutableLiveData<ApiResponse<List<ItemTvShow>>>()
        val client = ApiConf.getApiService().getTvShowsApi()
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    listTvShow.value = ApiResponse.success(response.body()?.results as List<ItemTvShow>)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e("getTvShow", "Not Success: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("getTvShow", "onFailure: ${t.message.toString()}")
            }
        })
        return listTvShow
    }

    fun getTvShowById(id: Int): LiveData<ApiResponse<ItemTvShow>> {
        EspressoIdlingResource.increment()
        val movie = MutableLiveData<ApiResponse<ItemTvShow>>()
        val client = ApiConf.getApiService().getTvShowById(id)
        client.enqueue(object : Callback<ItemTvShow> {
            override fun onResponse(
                call: Call<ItemTvShow>,
                response: Response<ItemTvShow>
            ) {
                if (response.isSuccessful) {
                    movie.value = ApiResponse.success(response.body() as ItemTvShow)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e("getMovieById", "Not Success: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ItemTvShow>, t: Throwable) {
                Log.e("getMovieById", "onFailure: ${t.message.toString()}")
            }
        })
        return movie
    }

    fun getMoviesByName(q: String): LiveData<ApiResponse<List<ItemMovie>>> {
        EspressoIdlingResource.increment()
        val listMovie = MutableLiveData<ApiResponse<List<ItemMovie>>>()
        val client = ApiConf.getApiService().getMoviesByName(q)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    listMovie.value = ApiResponse.success(response.body()?.results as List<ItemMovie>)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e("getMovieByName", "Not Success: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("getMovieByName", "onFailure: ${t.message.toString()}")
            }
        })
        return listMovie
    }

    fun getTvShowsByName(q: String): LiveData<ApiResponse<List<ItemTvShow>>> {
        EspressoIdlingResource.increment()
        val listTvShow = MutableLiveData<ApiResponse<List<ItemTvShow>>>()
        val client = ApiConf.getApiService().getTvShowByName(q)
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    listTvShow.value = ApiResponse.success(response.body()?.results as List<ItemTvShow>)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e("getTvShowByName", "Not Success: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("getTvShowByName", "onFailure: ${t.message.toString()}")
            }
        })
        return listTvShow
    }
}