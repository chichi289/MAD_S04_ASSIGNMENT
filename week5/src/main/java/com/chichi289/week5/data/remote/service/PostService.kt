package com.chichi289.week5.data.remote.service

import com.chichi289.week5.data.remote.model.DeletePost
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    @GET("v1/posts")
    fun getPosts(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<Unit>

    @GET("v1/posts/users/{userId}")
    fun getPostsByUserId(
        @Path("userId") userId: Int
    ): Response<Unit>

    @GET("v1/posts/{postId}")
    fun getPostDetail(
        @Path("postId") postId: Int
    ): Response<Unit>

    @DELETE("v1/posts/{postId}")
    fun deletePost(
        @Body deletePost: DeletePost
    ): Response<Unit>
}