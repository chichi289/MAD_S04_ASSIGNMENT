package com.chichi289.week5.domain

import com.chichi289.week5.data.remote.model.user.User
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertUser(user: User)
    fun getUser(): Flow<List<User>>
    suspend fun deleteUser(user: User)
}