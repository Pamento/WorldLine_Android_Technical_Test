package com.pawel.worldline_android_technical_test.data.mock.internal

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.mockwebserver.MockResponse
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AssetProvider @Inject constructor(@ApplicationContext private val context: Context) {

    fun createResponseFromAssets(
        fileName: String,
        responseCode: Int = DEFAULT_HTTP_CODE,
        delayInSeconds: Long = DEFAULT_DELAY
    ) : MockResponse {
        val inputStream = context.assets.open(fileName)

        val s = Scanner(inputStream, Charsets.UTF_8.name())
        val result = if (s.hasNext()) s.next() else ""

        return MockResponse()
            .setBody(result)
            .setResponseCode(responseCode)
            .setBodyDelay(delayInSeconds, TimeUnit.SECONDS)
    }

    companion object {
        private const val DEFAULT_DELAY = 1L
        private const val DEFAULT_HTTP_CODE = 200
    }
}