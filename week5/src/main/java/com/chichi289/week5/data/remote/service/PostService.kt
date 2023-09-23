package com.chichi289.week5.data.remote.service

import com.chichi289.week5.data.remote.model.DeletePostRequest
import com.chichi289.week5.data.remote.model.post.PostsResponse
import com.chichi289.week5.data.remote.model.post_detail.PostDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    @GET("v1/posts")
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<PostsResponse>

    @GET("v1/posts/users/{userId}")
    suspend fun getPostsByUserId(
        @Path("userId") userId: Int
    ): Response<PostsResponse>

    @GET("v1/posts/{postId}")
    suspend fun getPostDetail(
        @Path("postId") postId: Long
    ): Response<PostDetailResponse>

    @DELETE("v1/posts/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: Long,
        @Body request: DeletePostRequest
    ): Response<Unit>
}