package com.chichi289.week3.data.repository

import com.chichi289.week3.data.db.dao.UserDao
import com.chichi289.week3.data.model.User
import com.chichi289.week3.domain.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : DatabaseRepository {
    override fun insertAll(vararg users: User) {
        userDao.insertAll(*users)
    }

    override fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
}