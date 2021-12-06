package com.pawel.worldline_android_technical_test.data.api

import com.pawel.worldline_android_technical_test.util.Consts.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private fun httpInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun basicOkHttpClient() = OkHttpClient.Builder().addInterceptor(httpInterceptor()).build()

fun createNetworkService(): MoviesAPIs {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create()).client(basicOkHttpClient())
        .baseUrl(BASE_URL)
        .build()
    return retrofit.create(MoviesAPIs::class.java)
}