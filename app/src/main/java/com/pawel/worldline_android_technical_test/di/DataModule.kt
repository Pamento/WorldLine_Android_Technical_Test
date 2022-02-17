package com.pawel.worldline_android_technical_test.di

import com.pawel.worldline_android_technical_test.cache.MoviesInMemoryCache
import com.pawel.worldline_android_technical_test.data.repository.MoviesRepository
import com.pawel.worldline_android_technical_test.data.repository.MoviesRepositoryImpl
import com.pawel.worldline_android_technical_test.service.MoviesService
import com.pawel.worldline_android_technical_test.service.MoviesServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Declarations {
        @Binds
        abstract fun provideMoviesService(x: MoviesServiceImpl): MoviesService

        @Binds
        abstract fun provideMoviesRepository(x: MoviesRepositoryImpl): MoviesRepository
    }
//
//    @Provides
//    fun provideCacheInMemory() = MoviesInMemoryCache()
}
