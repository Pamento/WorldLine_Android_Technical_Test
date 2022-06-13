package com.pawel.data.interceptors

import android.util.Log
import com.pawel.common.HILT_APP_VERSION_NAME
import com.pawel.common.networkErrorHandling.MovieErrorCode
import com.pawel.common.networkErrorHandling.MovieException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

@SuppressWarnings("SwallowedException", "RethrowCaughtException")
class MovieInterceptor @Inject constructor(
    @Named(HILT_APP_VERSION_NAME) private val versionName: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        lateinit var response: Response
        try {
            val newRequest = builder
                .addMobileAppVersionToHeaders()
                .build()
            response = chain.proceed(newRequest)
            Log.i("network_monitoring", "intercept of response:code:: ${response.code}")
            Log.i("network_monitoring", "intercept of response:code:: $response")
            if (response.isSuccessful) {
                return response
            } else {
                throw MovieException(
                    MovieErrorCode(response.code),
                    response.message,
                    isHttpError = false
                )
            }
        } catch (e: HttpException) {
            throw MovieException(MovieErrorCode(e.code()), isHttpError = false, root = e)
        } catch (e: MovieException) {
          throw e
        } catch (e: IOException) {
            throw MovieException.genericError()
        }
    }

    private fun Request.Builder.addMobileAppVersionToHeaders(): Request.Builder {
        this.addHeader("Version_MobileApp", versionName)
        return this
    }
}
