package com.chichi289.week3.di

import com.chichi289.week3.domain.InMemoryUserRepository
import com.chichi289.week3.data.repository.InMemoryUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class UserModule {

    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: InMemoryUserRepositoryImpl): InMemoryUserRepository
}