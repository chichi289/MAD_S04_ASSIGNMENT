package com.chichi289.week2.domain

import com.chichi289.week2.data.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}