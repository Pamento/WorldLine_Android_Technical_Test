package com.pawel.worldline_android_technical_test.di

import com.pawel.worldline_android_technical_test.data.api.ApiHelper
import com.pawel.worldline_android_technical_test.data.api.ApiHelperImpl
import com.pawel.worldline_android_technical_test.data.repository.MoviesRepository
import com.pawel.worldline_android_technical_test.data.repository.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Declarations {
        @Binds
        abstract fun provideMoviesRepository(x: MoviesRepositoryImpl): MoviesRepository

        @Binds
        abstract fun provideApiHelper(x: ApiHelperImpl): ApiHelper
    }
}
