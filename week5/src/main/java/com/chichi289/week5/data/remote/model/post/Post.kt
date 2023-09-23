package com.chichi289.week5.data.remote.model.post


import androidx.annotation.Keep
import com.chichi289.week5.data.remote.model.user.User
import com.google.gson.annotations.SerializedName

@Keep
data class Post(
    @SerializedName("caption")
    val caption: String,
    @SerializedName("commentsCount")
    val commentsCount: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("likesCount")
    val likesCount: Int,
    @SerializedName("postId")
    val postId: Long,
    @SerializedName("url")
    val url: String,
    @SerializedName("user")
    val user: User
)