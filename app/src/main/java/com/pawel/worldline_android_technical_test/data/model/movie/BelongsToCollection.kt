package com.pawel.worldline_android_technical_test.data.model.movie

import com.squareup.moshi.Json

class BelongsToCollection {
    @Json(name = "id")
    var id: Int? = null

    @Json(name = "name")
    var name: String? = null

    @Json(name = "poster_path")
    var posterPath: String? = null

    @Json(name = "backdrop_path")
    var backdropPath: String? = null
}