package com.chichi289.assignments.data

import com.chichi289.assignments.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

/*
* Directly injected UserRepositoryImpl instead of UserRepository from AppModule
* due to Dagger hilt compile time error. I will fix it in future
* */

@Singleton
class UserRepositoryImpl @Inject constructor() {

    private fun generateRandomUser(): User {

        // 8-digit random long userId
        val userId = Random.nextLong(10000001, 88888888)

        // 6-character alphanumeric username
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        val userName = (1..6)
            .map { allowedChars.random() }
            .joinToString("")

        // Random full name up to 20 characters
        val fullNameLength = Random.nextInt(5, 20)
        val fullName = (1..fullNameLength)
            .map { ('A'..'Z').random() }
            .joinToString("")

        // Dummy email
        val email = "${userName.lowercase()}@xyz.com"

        return User(
            userId = userId,
            userName = userName,
            fullName = fullName,
            email = email
        )
    }

    suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            generateSequence { generateRandomUser() }.take(100).toList()
        }
    }
}