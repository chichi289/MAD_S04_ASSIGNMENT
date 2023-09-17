package com.chichi289.week4.data.remote

import com.chichi289.week4.data.remote.model.UserDetail
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API endpoint: https://picsum.photos/v2/list?page=1&limit=30
 */

interface PicsumPhotosService {

    @GET("list")
    suspend fun getUserDetails(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<UserDetail>
}