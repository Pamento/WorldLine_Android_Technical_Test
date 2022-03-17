package com.pawel.movieapp.conf

import com.pawel.data.di.URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class EnvConf {

    @Provides
    @Named(URL)
    fun provideUrl(): String = "https://api.themoviedb.org/3/"
}
