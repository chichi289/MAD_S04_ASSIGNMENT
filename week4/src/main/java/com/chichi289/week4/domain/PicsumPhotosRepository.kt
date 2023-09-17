package com.chichi289.week4.domain

import com.chichi289.week4.data.remote.model.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PicsumPhotosRepository {
    fun getPhotos(page: Int, limit: Int): Flow<NetworkResult<Unit>>

}