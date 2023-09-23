package com.chichi289.week5.data.remote.model.post


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PostPagingData(
    @SerializedName("currentPage")
    val currentPage: Int,
    @SerializedName("hasNextPage")
    val hasNextPage: Boolean,
    @SerializedName("posts")
    val posts: List<Post>
)