package com.chichi289.week4.di

import com.chichi289.week4.data.repository.DatabaseRepositoryImpl
import com.chichi289.week4.domain.InMemoryRepository
import com.chichi289.week4.data.repository.InMemoryRepositoryImpl
import com.chichi289.week4.data.repository.LocalRepositoryImpl
import com.chichi289.week4.domain.DatabaseRepository
import com.chichi289.week4.domain.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideInMemoryUserRepository(inMemoryRepositoryImpl: InMemoryRepositoryImpl): InMemoryRepository

    @Binds
    abstract fun provideDatabaseRepository(databaseRepositoryImpl: DatabaseRepositoryImpl): DatabaseRepository

    @Binds
    abstract fun provideLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}