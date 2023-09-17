package com.chichi289.week4.data.remote.model

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T> : NetworkResult<T>()
    class Success<T>(data: T?) : NetworkResult<T>(data = data)
    class Error<T>(message: String) : NetworkResult<T>(message = message)
    class NoInternetError<T>(message: String) : NetworkResult<T>(message = message)
}