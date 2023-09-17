package com.chichi289.week4.domain

import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    var usersAddedInDb: Flow<Boolean>

    suspend fun setUsersAddedToDb(b: Boolean)

}