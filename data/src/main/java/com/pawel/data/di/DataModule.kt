package com.pawel.data.di

import com.pawel.data.repository.MoviesRepositoryImpl
import com.pawel.domain.repository.MoviesRepository
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
    }
}
