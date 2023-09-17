package com.chichi289.week4.domain

import androidx.paging.PagingData
import com.chichi289.week4.data.remote.model.NetworkResult
import com.chichi289.week4.data.remote.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface PicsumPhotosRepository {
    fun getPhotos(limit: Int): Flow<PagingData<UserDetail>>

}