package com.chichi289.week3.domain

import com.chichi289.week3.data.model.User

interface InMemoryUserRepository {
    fun getUsers(): List<User>
}