package com.yofi.moviecatalogue.data.source.api

import com.yofi.moviecatalogue.BuildConfig
import com.yofi.moviecatalogue.data.source.response.ItemMovie
import com.yofi.moviecatalogue.data.source.response.ItemTvShow
import com.yofi.moviecatalogue.data.source.response.MovieResponse
import com.yofi.moviecatalogue.data.source.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("tv/popular")
    fun getTvShowsApi(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_TOKEN
    ): Call<TvShowResponse>

    @GET("movie/popular")
    fun getMoviesApi(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_TOKEN
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMoviesById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_TOKEN
    ): Call<ItemMovie>

    @GET("tv/{tv_id}")
    fun getTvShowById(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_TOKEN
    ): Call<ItemTvShow>

    @GET("search/movie")
    fun getMoviesByName(
        @Query("query") q: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_TOKEN
    ): Call<MovieResponse>

    @GET("search/tv")
    fun getTvShowByName(
        @Query("query") q: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_TOKEN
    ): Call<TvShowResponse>
}