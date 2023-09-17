package com.chichi289.week4.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API endpoint: https://random-data-api.com/api/v2/users?size=1
 */

//
interface RandomDataService {

    @GET("api/v2/users")
    suspend fun getUserData(@Query("size") size: Int): Response<Unit>

}