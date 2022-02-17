package com.pawel.worldline_android_technical_test.di

import com.pawel.worldline_android_technical_test.data.api.ApiHelper
import com.pawel.worldline_android_technical_test.data.api.MoviesAPIs
import com.pawel.worldline_android_technical_test.util.Consts
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(SingletonComponent::class)
@Module
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
    fun httpInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun basicOkHttpClient() =
        OkHttpClient.Builder().addInterceptor(httpInterceptor()).build()


    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    fun provideApiHelper(apiHelper: ApiHelper): ApiHelper {
        return apiHelper
    }
}