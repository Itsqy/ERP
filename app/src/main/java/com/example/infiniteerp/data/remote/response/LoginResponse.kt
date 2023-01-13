package com.example.infiniteerp.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("token")
    val token: String,

    @field:SerializedName("user")
    val user: User,

    @field:SerializedName("auth")
    val auth: String,

    @field:SerializedName("response")
    val response: String,
)

data class User(

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("token")
    val token: String,

    @field:SerializedName("user")
    val user: User,

    @field:SerializedName("auth")
    val auth: String,

    )



