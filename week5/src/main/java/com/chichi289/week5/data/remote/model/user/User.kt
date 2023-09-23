package com.chichi289.week5.data.remote.model.user


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class User(
    @SerializedName("biography")
    val biography: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("postsCount")
    val postsCount: Int,
    @SerializedName("profilePicUrl")
    val profilePicUrl: String,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("username")
    val username: String
)