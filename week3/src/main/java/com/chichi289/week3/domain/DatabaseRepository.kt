package com.chichi289.week3.domain

import com.chichi289.week3.data.model.User
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun insertAll(vararg users: User)

    fun getAllUsers(): Flow<List<User>>

}