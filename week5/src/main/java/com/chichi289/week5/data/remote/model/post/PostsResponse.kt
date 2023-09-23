package com.chichi289.week5.data.remote.model.post


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PostsResponse(
    @SerializedName("data")
    val `data`: PostPagingData,
    @SerializedName("status")
    val status: Boolean
)