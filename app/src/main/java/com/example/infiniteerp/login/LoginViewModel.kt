package com.example.infiniteerp.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infiniteerp.data.model.UserModel
import com.example.infiniteerp.data.model.UserPreferences
import com.example.infiniteerp.data.remote.response.LoginResponse
import com.example.infiniteerp.data.remote.retrofit.ApiConfig
import com.example.infiniteerp.utils.Helpers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val pref: UserPreferences) :
    ViewModel() {
    companion object {
        private const val TAG = "SignInViewModel"
        private const val SUCCESS = "success"
    }

    fun login(email: String, pass: String, callback: Helpers.ApiCallbackString) {

        val service =
            ApiConfig().getApiService().login(email, pass, "62704287CEB744C7BA2CF4E4E39335D7", "*")

        service.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.success) {
                        callback.onResponse(response.body() != null, SUCCESS)
                        val model = UserModel(
                            email,
                            pass,
                            "62704287CEB744C7BA2CF4E4E39335D7",
                            "*",
                            responseBody.token,
                            true
                        )
                        saveUser(model)

                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                        if (responseBody != null) {
                            callback.onResponse(false, responseBody.response)
                        }
                    }
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                callback.onResponse(false, t.message.toString())
            }
        })

    }

    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }


}