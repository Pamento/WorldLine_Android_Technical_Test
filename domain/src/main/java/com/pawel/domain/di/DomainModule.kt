package com.pawel.domain.di

import com.pawel.domain.service.MoviesService
import com.pawel.domain.service.MoviesServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Declarations {
        @Suppress("unused")
        @Binds
        abstract fun provideMoviesService(x: MoviesServiceImpl): MoviesService
    }
}