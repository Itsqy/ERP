package com.example.infiniteerp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

class DataPreferences {
    private val prefs: SharedPreferences

    constructor(context: Context) {
        prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    fun getAuthCredentials(): String {
        val email = prefs.getString("email", "")
        val password = prefs.getString("password", "")
        return "$email:$password"
    }

    fun saveAuthCredentials(email: String, password: String) {
        val editor = prefs.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }
}
