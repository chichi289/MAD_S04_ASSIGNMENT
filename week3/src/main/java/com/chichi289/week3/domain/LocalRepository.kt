package com.chichi289.week3.domain

import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    var isUserStoredInDb: Flow<Boolean>

    suspend fun setUserAddedToDb(b: Boolean)

}