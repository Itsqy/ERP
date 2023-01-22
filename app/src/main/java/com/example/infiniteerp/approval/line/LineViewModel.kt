package com.example.infiniteerp.approval.line

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infiniteerp.data.remote.response.LineReponse
import com.example.infiniteerp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LineViewModel(var lineActivity: LineActivity) : ViewModel() {
    companion object {
        private const val TAG = "LineViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun showListLine(userName: String, passWord: String, id: String) {
        _isLoading.value = true
        ApiConfig(userName, passWord).getApiServiceHeader()
            .getOrderLine("salesOrder='$id'")
            .enqueue(object : Callback<LineReponse> {
                override fun onResponse(
                    call: Call<LineReponse>,
                    response: Response<LineReponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {

                        val responseBody = response.body()
                        if (responseBody != null) {
                            if (responseBody.response.data.isNotEmpty()) {
                                lineActivity.setListOrderLine(responseBody.response.data)
                            } else {
                                lineActivity.showEmpty()
                            }
                        }
                    } else {
                        _isLoading.value = false
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<LineReponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                    Toast.makeText(
                        lineActivity,
                        "onFailure: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}