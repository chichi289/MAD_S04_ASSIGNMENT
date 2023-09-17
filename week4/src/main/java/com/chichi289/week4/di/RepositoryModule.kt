package com.chichi289.week4.di

import com.chichi289.week4.data.PicsumPhotosRepositoryImpl
import com.chichi289.week4.data.RandomDataRepositoryImpl
import com.chichi289.week4.domain.PicsumPhotosRepository
import com.chichi289.week4.domain.RandomDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideRandomDataRepository(randomDataRepositoryImpl: RandomDataRepositoryImpl): RandomDataRepository


    @Binds
    abstract fun providePicsumPhotosRepository(picsumPhotosRepositoryImpl: PicsumPhotosRepositoryImpl): PicsumPhotosRepository

}