package com.pawel.worldline_android_technical_test.di

import com.pawel.worldline_android_technical_test.data.api.ApiHelper
import com.pawel.worldline_android_technical_test.data.api.ApiHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ApiHelperModule {

    @Binds
    abstract fun bindApiHelper(impl: ApiHelperImpl): ApiHelper

}