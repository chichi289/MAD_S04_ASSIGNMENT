package com.chichi289.week5.data.remote.model.user


import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
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
    @PrimaryKey(autoGenerate = false)
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("username")
    val username: String
)