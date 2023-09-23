package com.chichi289.week5.data.repository

import com.chichi289.week5.data.local.UserDao
import com.chichi289.week5.data.remote.model.user.User
import com.chichi289.week5.domain.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : LocalRepository {
    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override fun getUser(): Flow<List<User>> = userDao.getUser()

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}