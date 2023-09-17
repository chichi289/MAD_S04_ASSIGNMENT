package com.chichi289.week4.domain

import com.chichi289.week4.data.model.User

interface InMemoryRepository {
    fun getUsers(count:Int): List<User>
}