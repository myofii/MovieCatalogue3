package com.yofi.moviecatalogue.data.source.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<ItemMovie>,

    @field:SerializedName("total_results")
    val totalResults: Int
)

data class ItemMovie(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("overview")
    val overview: String? = null
)