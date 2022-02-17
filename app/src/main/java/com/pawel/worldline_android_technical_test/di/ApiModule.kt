package com.pawel.worldline_android_technical_test.di

import com.pawel.worldline_android_technical_test.data.api.ApiHelper
import com.pawel.worldline_android_technical_test.data.api.ApiHelperImpl
import com.pawel.worldline_android_technical_test.data.api.MoviesAPIs
import com.pawel.worldline_android_technical_test.data.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

//    @Provides
//    fun provideMoviesApis(moviesApis: MoviesAPIs): MoviesAPIs {
//        return moviesApis
//    }

//    @Singleton
//    @Provides
//    fun provideApiHelper(moviesApis: MoviesAPIs): ApiHelper {
//        return ApiHelperImpl(moviesApis)
//    }

//    @Singleton
//    @Binds
//    abstract fun bindApi(impl: ApiHelperImpl): ApiHelper
}