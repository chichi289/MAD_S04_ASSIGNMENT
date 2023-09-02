package com.chichi289.assignments.domain

import com.chichi289.assignments.data.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUsersNormal(): List<User>
}