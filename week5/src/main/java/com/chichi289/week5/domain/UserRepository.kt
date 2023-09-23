package com.chichi289.week5.domain

import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.data.remote.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getRandomUser(): Flow<NetworkResult<User>>

}