package com.chichi289.week3.domain

import com.chichi289.week3.data.model.User

interface InMemoryRepository {
    fun getUsers(count:Int): List<User>
}