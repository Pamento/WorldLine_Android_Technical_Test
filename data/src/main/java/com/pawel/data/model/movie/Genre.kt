package com.pawel.data.model.movie

import com.squareup.moshi.Json

class Genre {
    @Json(name = "id")
    var id: Int? = null

    @Json(name = "name")
    var name: String? = null
}