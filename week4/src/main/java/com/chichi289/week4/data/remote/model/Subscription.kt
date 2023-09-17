package com.chichi289.week4.data.remote.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Subscription(
    @SerializedName("payment_method")
    val paymentMethod: String,
    @SerializedName("plan")
    val plan: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("term")
    val term: String
)