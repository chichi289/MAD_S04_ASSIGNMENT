package com.chichi289.week4.data.remote

import com.chichi289.week4.data.remote.model.UserDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API endpoint: https://picsum.photos/v2/list?page=1&limit=30
 */

interface PicsumPhotosService {

    @GET("list")
    fun getUserDetails(@Query("page") page: Int, @Query("limit") limit: Int): Response<List<UserDetail>>
}