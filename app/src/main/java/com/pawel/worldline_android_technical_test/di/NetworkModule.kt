package com.pawel.worldline_android_technical_test.di

import com.pawel.worldline_android_technical_test.data.api.MoviesAPIs
import com.pawel.worldline_android_technical_test.util.Consts
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun createNetworkService(): MoviesAPIs {
        val retrofit = Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .client(basicOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .build()
        return retrofit.create(MoviesAPIs::class.java)
    }

    @Provides
    @Singleton
    fun httpInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun basicOkHttpClient() =
        OkHttpClient.Builder().addInterceptor(httpInterceptor()).build()


    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
}