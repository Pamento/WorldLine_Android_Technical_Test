package com.pawel.movieapp.di

import com.pawel.common.HILT_APP_VERSION_NAME
import com.pawel.movieapp.BuildConfig

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Named(HILT_APP_VERSION_NAME)
    fun provideVersionName() = BuildConfig.VERSION_NAME
}
