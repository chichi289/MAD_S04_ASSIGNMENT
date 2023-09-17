package com.chichi289.week4.data.remote.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CreditCard(
    @SerializedName("cc_number") val ccNumber: String
)