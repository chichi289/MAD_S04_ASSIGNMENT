package com.chichi289.week5.data.remote.model

sealed class NetworkResult<out T : Any> {
    object Loading : NetworkResult<Nothing>()
    class Success<out T : Any>(val data: T) : NetworkResult<T>()
    class Error(message: String) : NetworkResult<Nothing>()
    class NoInternetError(message: String) : NetworkResult<Nothing>()
}

/*
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T> : NetworkResult<T>()
    class Success<T>(data: T?) : NetworkResult<T>(data = data)
    class Error<T>(message: String) : NetworkResult<T>(message = message)
    class NoInternetError<T>(message: String) : NetworkResult<T>(message = message)
}*/
