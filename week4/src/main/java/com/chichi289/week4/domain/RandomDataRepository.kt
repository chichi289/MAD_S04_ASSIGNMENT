package com.chichi289.week4.domain

import com.chichi289.week4.data.remote.model.NetworkResult
import com.chichi289.week4.data.remote.model.User
import kotlinx.coroutines.flow.Flow

interface RandomDataRepository {
    suspend fun getUserData(size: Int): Flow<NetworkResult<User>>
}