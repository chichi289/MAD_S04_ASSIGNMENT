package com.chichi289.week5.data.remote.model.user


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Boolean
)