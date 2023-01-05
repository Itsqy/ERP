package com.example.infiniteerp.data.remote.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class LoginResponse(
    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("token")
    val token: String,

    @field:SerializedName("user")
    val user: Objects,

    @field:SerializedName("auth")
    val auth: String,
)


