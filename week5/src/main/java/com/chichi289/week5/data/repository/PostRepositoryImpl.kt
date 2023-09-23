package com.chichi289.week5.data.repository

import com.chichi289.week5.data.remote.model.DeletePost
import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.data.remote.model.post.Post
import com.chichi289.week5.data.remote.model.post.PostsResponse
import com.chichi289.week5.data.remote.model.post_detail.PostDetailResponse
import com.chichi289.week5.data.remote.service.PostService
import com.chichi289.week5.domain.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val postService: PostService
) : PostRepository {
    override fun getPosts(page: Int, size: Int): PostsResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getPostsByUserId(userId: Int): Flow<NetworkResult<List<Post>>> {
        return flow {
            emit(NetworkResult.Loading())
            val response = postService.getPostsByUserId(userId)
            if (response.isSuccessful && response.code() == 200) {
                val responseBody = response.body()
                if (responseBody?.status == true) {
                    emit(NetworkResult.Success(responseBody.data.posts))
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

    override suspend fun getPostDetail(postId: Int): Flow<NetworkResult<PostDetailResponse>> {
        TODO("Not yet implemented")
    }

    override fun deletePost(deletePost: DeletePost) {
        TODO("Not yet implemented")
    }
}