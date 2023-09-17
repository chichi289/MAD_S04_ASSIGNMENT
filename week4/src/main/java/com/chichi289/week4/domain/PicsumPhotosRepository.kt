package com.chichi289.week4.domain

import androidx.paging.PagingData
import com.chichi289.week4.data.remote.model.Post
import kotlinx.coroutines.flow.Flow

interface PicsumPhotosRepository {
    val posts: Flow<PagingData<Post>>

}