package com.yofi.moviecatalogue.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class DataEntity (
    var id: String? = null,
    var type: String? = null,
    var name: String? = null,
    var year: String? = null,
    var rating: String? = null,
    var desc: String? = null,
    var genre: String? = null,
    var poster: String? = null
)