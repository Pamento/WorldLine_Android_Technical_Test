package com.pawel.worldline_android_technical_test.data.model.movies

import com.squareup.moshi.Json

class Result {
    @Json(name = "adult")
    var isAdult = false

    @Json(name = "backdrop_path")
    var backdropPath: String? = null

    @Json(name = "genre_ids")
    var genreIds: List<Int>? = null

    @Json(name = "id")
    var id = 0

    @Json(name = "original_language")
    var originalLanguage: String? = null

    @Json(name = "original_title")
    var originalTitle: String? = null

    @Json(name = "overview")
    var overview: String? = null

    @Json(name = "popularity")
    var popularity = 0.0

    @Json(name = "poster_path")
    var posterPath: String? = null

    @Json(name = "release_date")
    var releaseDate: String? = null

    @Json(name = "title")
    var title: String? = null

    @Json(name = "video")
    var isVideo = false

    @Json(name = "vote_average")
    var voteAverage = 0.0

    @Json(name = "vote_count")
    var voteCount = 0
}