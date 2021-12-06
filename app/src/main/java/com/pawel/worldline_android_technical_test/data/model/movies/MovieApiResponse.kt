package com.pawel.worldline_android_technical_test.data.model.movies

import com.pawel.worldline_android_technical_test.data.model.movies.Result
import com.squareup.moshi.Json

class MovieApiResponse {

    @Json(name = "page")
    private var page: Int? = null

    @Json(name = "results")
    private var results: List<Result?>? = null

    @Json(name = "total_pages")
    private var totalPages: Int? = null

    @Json(name = "total_results")
    private var totalResults: Int? = null
}