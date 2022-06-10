package com.pawel.data.di

import com.pawel.data.repository.ImageRepositoryImpl
import com.pawel.domain.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ImageModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class GlideDeclaration {
        @Binds
        abstract fun bindGlideRepository(g: ImageRepositoryImpl): ImageRepository
    }
}
