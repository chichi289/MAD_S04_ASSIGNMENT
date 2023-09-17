package com.chichi289.week4.data

import com.chichi289.week4.data.remote.RandomDataService
import com.chichi289.week4.data.remote.model.NetworkResult
import com.chichi289.week4.domain.RandomDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RandomDataRepositoryImpl @Inject constructor(
    private val randomDataService: RandomDataService
) : RandomDataRepository {
    override suspend fun getUserData(size: Int): Flow<NetworkResult<Unit>> {
        return flow {
            emit(NetworkResult.Loading())
            val response = randomDataService.getUserData(size = size)
            if (response.isSuccessful && response.code() == 200 && response.body() != null) {
                emit(NetworkResult.Success(response.body()))
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