package com.chichi289.week5.data.remote.service

import com.chichi289.week5.data.remote.model.user.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("v1/users/random")
    fun getRandomUser(): Response<UserResponse>

}