package com.pawel.data.di

import com.pawel.data.api.MoviesRepositoryNetwork
import com.pawel.data.repository.MoviesRepositoryImpl
import com.pawel.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Declarations {
        @Suppress("unused")
        @Binds
        abstract fun provideMoviesRepository(x: MoviesRepositoryImpl): MoviesRepository
    }

    @Provides
    fun provideMoviesRepositoryNetwork(retrofit: Retrofit): MoviesRepositoryNetwork =
        retrofit.create(MoviesRepositoryNetwork::class.java)
}
