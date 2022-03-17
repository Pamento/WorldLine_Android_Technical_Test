package com.pawel.worldline_android_technical_test.di

import com.pawel.common.HILT_APP_VERSION_NAME
import com.pawel.worldline_android_technical_test.BuildConfig
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