package com.chichi289.week5.domain

import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("v1/users/random")
    fun getRandomUser(): Response<Unit>

}