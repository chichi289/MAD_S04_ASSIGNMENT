package com.chichi289.assignments.data

import com.chichi289.assignments.data.model.User
import com.chichi289.assignments.domain.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class UserDataSource : UserRepository {

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
        val fullName = (5..fullNameLength)
            .map { ('A'..'Z').random() }
            .joinToString("")

        // Dummy email
        val email = "${userName.lowercase()}@example.com"

        return User(
            userId = userId,
            userName = userName,
            fullName = fullName,
            email = email
        )
    }

    override suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            generateSequence { generateRandomUser() }.take(100).toList()
        }
    }

    override suspend fun getUsersNormal(): List<User> {
        val list = ArrayList<User>()
        repeat(100) {
            list.addAll(generateSequence { generateRandomUser() }.take(1).toList())
        }
        return list
    }
}