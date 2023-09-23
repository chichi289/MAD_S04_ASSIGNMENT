package com.chichi289.week5.data.remote.model.post_detail


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PostDetailResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Boolean
)