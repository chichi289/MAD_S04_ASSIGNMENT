package com.chichi289.week4.data.remote.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class User(
    @SerializedName("address")
    val address: Address,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("credit_card")
    val creditCard: CreditCard,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("employment")
    val employment: Employment,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("social_insurance_number")
    val socialInsuranceNumber: String,
    @SerializedName("subscription")
    val subscription: Subscription,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("username")
    val username: String
)