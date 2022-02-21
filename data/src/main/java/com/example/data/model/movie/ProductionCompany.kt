package com.example.data.model.movie

import com.squareup.moshi.Json

class ProductionCompany {
    @Json(name = "id")
    var id: Int? = null

    @Json(name = "logo_path")
    var logoPath: String? = null

    @Json(name = "name")
    var name: String? = null

    @Json(name = "origin_country")
    var originCountry: String? = null
}