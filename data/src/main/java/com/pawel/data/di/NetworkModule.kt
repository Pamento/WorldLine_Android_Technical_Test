package com.pawel.data.di

import com.pawel.data.api.MoviesRepositoryNetwork
import com.pawel.domain.util.Consts
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
    fun provideRepositoryNetwork(): MoviesRepositoryNetwork {
        val retrofit = Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .client(provideHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .build()
        return retrofit.create(MoviesRepositoryNetwork::class.java)
    }

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
            .build()


    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
}