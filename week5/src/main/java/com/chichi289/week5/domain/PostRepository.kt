package com.chichi289.week5.domain

import androidx.paging.PagingData
import com.chichi289.week5.data.remote.model.DeletePost
import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.data.remote.model.post.Post
import com.chichi289.week5.data.remote.model.post.PostsResponse
import com.chichi289.week5.data.remote.model.post_detail.PostDetailResponse
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    val posts: Flow<PagingData<Post>>

    suspend fun getPostsByUserId(userId: Int): Flow<NetworkResult<List<Post>>>

    suspend fun getPostDetail(postId: Long): Flow<NetworkResult<Post>>

    fun deletePost(deletePost: DeletePost): Unit

}