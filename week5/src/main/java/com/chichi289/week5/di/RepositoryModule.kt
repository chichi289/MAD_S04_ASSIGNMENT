package com.chichi289.week5.di

import com.chichi289.week5.data.repository.LocalRepositoryImpl
import com.chichi289.week5.data.repository.PostRepositoryImpl
import com.chichi289.week5.data.repository.UserRepositoryImpl
import com.chichi289.week5.domain.LocalRepository
import com.chichi289.week5.domain.PostRepository
import com.chichi289.week5.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun provideLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository

    @Binds
    abstract fun providePostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository
}