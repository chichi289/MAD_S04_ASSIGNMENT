package com.chichi289.week5.data.repository

import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.data.remote.model.user.User
import com.chichi289.week5.data.remote.service.UserService
import com.chichi289.week5.domain.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun getRandomUser(): Flow<NetworkResult<User>> {
        return flow {
            emit(NetworkResult.Loading())
            val response = userService.getRandomUser()
            if (response.isSuccessful && response.code() == 200) {
                val userResponse = response.body()
                if (userResponse?.status == true) {
                    emit(NetworkResult.Success(userResponse.data.user))
                }
            } else {
                emit(NetworkResult.Error(response.message()))
            }
        }
            .flowOn(Dispatchers.IO)
            .catch { exception ->
                if (exception is UnknownHostException) {
                    emit(NetworkResult.NoInternetError("No internet connection"))
                } else {
                    emit(NetworkResult.Error(exception.message.toString()))
                }
            }
    }

}