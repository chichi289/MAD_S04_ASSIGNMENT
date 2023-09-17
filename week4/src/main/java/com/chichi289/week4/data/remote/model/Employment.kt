package com.chichi289.week4.data.remote.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class Employment(
    @SerializedName("key_skill")
    val keySkill: String,
    @SerializedName("title")
    val title: String
)