package com.yofi.moviecatalogue.data.source.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<ItemTvShow>,

    @field:SerializedName("total_results")
    val totalResults: Int
)

data class ItemTvShow(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("overview")
    val overview: String? = null
)
