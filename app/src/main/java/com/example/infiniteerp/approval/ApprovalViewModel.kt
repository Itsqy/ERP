package com.example.infiniteerp.approval

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.infiniteerp.data.model.UserModel
import com.example.infiniteerp.data.model.UserPreferences
import kotlinx.coroutines.launch

class ApprovalViewModel(private val pref: UserPreferences) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

}