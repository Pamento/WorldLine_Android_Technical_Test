package com.pawel.data.model.movie

import com.squareup.moshi.Json

class ProductionCountry {
    @Json(name = "iso_3166_1")
    var iso31661: String? = null

    @Json(name = "name")
    var name: String? = null
}