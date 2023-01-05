package com.example.infiniteerp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserModel(
    val username: String,
    val password: String,
    val clientId: String,
    val orgId: String,
    val token: String,
    val isLogin: Boolean
) : Parcelable
