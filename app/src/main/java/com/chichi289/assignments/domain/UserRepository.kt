package com.chichi289.assignments.domain

import com.chichi289.assignments.data.model.User

/*
* NOT USED AS DAGGER HILT CAUSES ISSUE WHILE PROVIDING DEPENDENCIES
* BY AppModule.kt
* */
interface UserRepository {
    suspend fun getUsers(): List<User>
}