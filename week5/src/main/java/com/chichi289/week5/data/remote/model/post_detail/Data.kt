package com.chichi289.week5.data.remote.model.post_detail


import androidx.annotation.Keep
import com.chichi289.week5.data.remote.model.post.Post
import com.google.gson.annotations.SerializedName

@Keep
data class Data(
    @SerializedName("post")
    val post: Post
)