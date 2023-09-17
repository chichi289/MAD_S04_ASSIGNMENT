package com.chichi289.week4.domain

import androidx.paging.PagingData
import com.chichi289.week4.data.remote.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface PicsumPhotosRepository {
    val getPhotos: Flow<PagingData<UserDetail>>

}