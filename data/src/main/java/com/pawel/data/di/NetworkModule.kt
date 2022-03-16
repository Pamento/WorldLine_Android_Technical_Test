package com.pawel.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pawel.data.interceptors.MovieInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


//const val URL = "https://api.themoviedb.org/3/"
const val URL = "URL"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient, @Named(URL) url: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideHttpInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(provideHttpInterceptor())
            .addInterceptor(MovieInterceptor("HILT_APP_VERSION_NAME"))
            .build()
}