package com.chichi289.week5.di

import android.content.Context
import androidx.room.Room
import com.chichi289.week5.data.local.UserDao
import com.chichi289.week5.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext applicationContext: Context): UserDatabase =
        Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "user-database"
        ).build()


    @Singleton
    @Provides
    fun providerUserDao(userDatabase: UserDatabase): UserDao = userDatabase.userDao()
}