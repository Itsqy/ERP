package com.example.infiniteerp.utils

object Helpers {
    interface ApiCallbackString {
        fun onResponse(success: Boolean, message: String)
    }
}