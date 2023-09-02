package com.chichi289.week2.data

import com.chichi289.week2.data.model.User
import com.chichi289.week2.domain.UserRepository
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
        val fullNameLength = Random.nextInt(1, 20)
        val fullName = (1..fullNameLength)
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

    override fun getUsers(): List<User> {
        return generateSequence {
            generateRandomUser()
        }.take(10).toList()
    }
}