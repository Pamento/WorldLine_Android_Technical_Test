package com.pawel.movieapp.data.mock.internal

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.mockwebserver.MockResponse
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AssetProvider @Inject constructor(@ApplicationContext private val context: Context) {

    fun createResponseFromAssets(
        fileName: String,
        responseCode: Int = DEFAULT_HTTP_CODE,
        delayInSeconds: Long = DEFAULT_DELAY
    ): MockResponse {
        var inputStream = ""
        try {
            inputStream = context.assets.open(fileName).bufferedReader(Charsets.UTF_8).use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

        return MockResponse()
            .setBody(inputStream)
            .setResponseCode(responseCode)
            .setBodyDelay(delayInSeconds, TimeUnit.SECONDS)
    }

    companion object {
        private const val DEFAULT_DELAY = 1L
        private const val DEFAULT_HTTP_CODE = 200
    }
}
