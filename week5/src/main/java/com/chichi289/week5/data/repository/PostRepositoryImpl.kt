package com.chichi289.week5.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chichi289.week5.data.remote.model.DeletePostRequest
import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.data.remote.model.post.Post
import com.chichi289.week5.data.remote.service.PostService
import com.chichi289.week5.domain.PostRepository
import com.chichi289.week5.presentation.home.PostsDataSource
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

    override val posts: Flow<PagingData<Post>> =
        Pager(config = PagingConfig(pageSize = 10)) {
            PostsDataSource(postService = postService)
        }.flow

    override suspend fun getPostsByUserId(userId: Int): Flow<NetworkResult<List<Post>>> {
        return flow {
            emit(NetworkResult.Loading)
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

    override suspend fun getPostDetail(postId: Long): Flow<NetworkResult<Post>> {
        return flow {
            emit(NetworkResult.Loading)
            val response = postService.getPostDetail(postId)
            if (response.isSuccessful && response.code() == 200) {
                val responseBody = response.body()
                if (responseBody?.status == true) {
                    emit(NetworkResult.Success(responseBody.data.post))
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

    override suspend fun deletePost(postId: Long, userId: Long): Flow<NetworkResult<Unit>> {
        val deletePost = DeletePostRequest(userId)
        return flow {
            emit(NetworkResult.Loading)
            val response = postService.deletePost(
                postId = postId.toInt(),
                deletePostRequest = deletePost
            )
            if (response.isSuccessful && response.code() == 204) {
                val responseBody = response.body() ?: Unit
                emit(NetworkResult.Success(responseBody))
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