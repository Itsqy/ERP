package com.example.infiniteerp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.infiniteerp.approval.ApprovalViewModel
import com.example.infiniteerp.approval.line.LineViewModel
import com.example.infiniteerp.data.model.UserPreferences
import com.example.infiniteerp.login.LoginViewModel

class ViewModelFactory(private val pref: UserPreferences) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            modelClass.isAssignableFrom(ApprovalViewModel::class.java) -> {
                ApprovalViewModel(pref) as T
            }
//            modelClass.isAssignableFrom(LineViewModel::class.java) -> {
//                LineViewModel(
//                    pref
//                ) as T
//            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

}