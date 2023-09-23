package com.chichi289.week5.data.remote.model.user


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Data(
    @SerializedName("user")
    val user: User
)