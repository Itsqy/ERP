package com.example.infiniteerp.approval.draft

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infiniteerp.data.remote.response.PurchaseOrderResponse
import com.example.infiniteerp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DraftViewModel(var mainFragment: DraftFragment) : ViewModel() {
    companion object {
        private const val TAG = "ReleaseViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun showListRelease(userName: String, passWord: String, docStatus: String, posted: Boolean) {
        _isLoading.value = true
        ApiConfig(
            userName,
            passWord,
        ).getApiServiceHeader()
            .getHeader(
                "salesTransaction=$posted and documentStatus='$docStatus'"
            )
            .enqueue(object : Callback<PurchaseOrderResponse> {
                override fun onResponse(
                    call: Call<PurchaseOrderResponse>,
                    response: Response<PurchaseOrderResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {

                        val responseBody = response.body()
                        if (responseBody != null) {
                            mainFragment?.setListOrder(responseBody?.response?.data)


                        }
                    } else {
                        _isLoading.value = false
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<PurchaseOrderResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
    }

    fun searchHeaderList(userName: String, passWord: String, id: String) {
        _isLoading.value = true
        ApiConfig(
            userName,
            passWord,
        ).getApiServiceHeader()
            .searchHeader(
                "documentNo='$id'and salesTransaction=false and documentStatus='DR'"
            )
            .enqueue(object : Callback<PurchaseOrderResponse> {
                override fun onResponse(
                    call: Call<PurchaseOrderResponse>,
                    response: Response<PurchaseOrderResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {

                        val responseBody = response.body()
                        if (responseBody != null) {
                            var result = responseBody?.response?.data

                            mainFragment?.setResultSearch(result)

                        }
                    } else {
                        _isLoading.value = false
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<PurchaseOrderResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
    }


}