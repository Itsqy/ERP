package com.example.infiniteerp.utils

import android.widget.Toast
import com.example.infiniteerp.login.LoginActivity

object Helpers {
    fun showDialog(loginActivity: LoginActivity, msg: String) {
        Toast.makeText(loginActivity, "$msg", Toast.LENGTH_SHORT).show()
    }

    interface ApiCallbackString {
        fun onResponse(success: Boolean, message: String)
    }
}