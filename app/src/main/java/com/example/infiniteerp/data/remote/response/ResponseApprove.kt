package com.example.infiniteerp.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseApprove(

    @field:SerializedName("message")
    val message: Approve
)

@Parcelize
data class Approve(
    @field:SerializedName("text")
    val text: String,

    @field:SerializedName("severity")
    val severity: String,


    ) : Parcelable
