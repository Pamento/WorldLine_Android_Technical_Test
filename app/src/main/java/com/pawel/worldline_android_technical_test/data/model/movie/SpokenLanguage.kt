package com.pawel.worldline_android_technical_test.data.model.movie

import com.squareup.moshi.Json

class SpokenLanguage {
    @Json(name = "english_name")
    var englishName: String? = null

    @Json(name = "iso_639_1")
    var iso6391: String? = null

    @Json(name = "name")
    var name: String? = null
}