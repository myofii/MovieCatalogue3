package com.yofi.moviecatalogue.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvShow")
data class TvShowEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "original_name")
    val originalName: String? = null,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String? = null,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
)