package com.chichi289.week2.domain

import com.chichi289.week2.data.model.User

interface UserRepository {
    fun getUsers(): List<User>
}