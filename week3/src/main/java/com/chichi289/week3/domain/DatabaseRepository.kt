package com.chichi289.week3.domain

import com.chichi289.week3.data.model.User
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun insertUsers(vararg users: User)

    fun getUsers(): Flow<List<User>>

    fun deleteUser(user: User)

}