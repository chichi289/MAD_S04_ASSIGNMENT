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

    override fun insertUsers(vararg users: User) {
        userDao.insertAll(*users)
    }

    override fun getUsers(): Flow<List<User>> = userDao.getAllUsers()

    override fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}