package com.chichi289.week5.domain

import androidx.paging.PagingData
import com.chichi289.week5.data.remote.model.DeletePostRequest
import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.data.remote.model.post.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    val posts: Flow<PagingData<Post>>

    suspend fun getPostsByUserId(userId: Int): Flow<NetworkResult<List<Post>>>

    suspend fun getPostDetail(postId: Long): Flow<NetworkResult<Post>>

    suspend fun deletePost(deletePost: DeletePostRequest): Unit

}