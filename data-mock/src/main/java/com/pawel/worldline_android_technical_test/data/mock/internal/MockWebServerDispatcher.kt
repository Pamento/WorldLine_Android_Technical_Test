package com.pawel.worldline_android_technical_test.data.mock.internal

import android.util.Log
import com.pawel.worldline_android_technical_test.common.BuildConfig
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import javax.inject.Inject

class MockWebServerDispatcher @Inject constructor(private val assets: AssetProvider) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        Log.i("mock_server", "dispatch: MOCK_WEB_SERVER_DISPATCHER")
        return when (request.path) {
            "/movie/popular?api_key=" + BuildConfig.API_KEY + "&language=fr=&page=1" -> assets.createResponseFromAssets("movies.json")
            "/movie/646385?api_key=" + BuildConfig.API_KEY + "&language=fr=&page=1" -> assets.createResponseFromAssets("movie_id_646385.json")
            else -> throw IllegalArgumentException("Mocked URL not handled")
        }
    }
}