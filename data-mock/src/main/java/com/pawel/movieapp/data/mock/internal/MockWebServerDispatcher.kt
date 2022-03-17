package com.pawel.movieapp.data.mock.internal

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import javax.inject.Inject

class MockWebServerDispatcher @Inject constructor(private val assets: AssetProvider) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (val symbol = request.path?.substringAfter("/movie/")?.substringBefore("?")) {
            "popular" -> assets.createResponseFromAssets("movies.json")
            "646385","634649","476669" -> assets.createResponseFromAssets("movie_id_$symbol.json")
            else -> throw IllegalArgumentException("Mocked URL not handled")
        }
    }
}