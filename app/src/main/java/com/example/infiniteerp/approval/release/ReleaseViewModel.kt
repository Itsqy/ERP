package com.example.infiniteerp.approval.release

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.infiniteerp.data.model.UserPreferences
import com.example.infiniteerp.data.remote.response.PurchaseOrderResponse
import com.example.infiniteerp.data.remote.retrofit.ApiConfig

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReleaseViewModel(var mainFragment: ReleaseFragment) : ViewModel() {
    companion object {
        private const val TAG = "ReleaseViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun showListRelease(userName: String, passWord: String, docStatus: String, posted: Boolean) {
        _isLoading.value = true
        ApiConfig(userName, passWord).getApiServiceHeader()
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
                            var result = responseBody?.response?.data
                            mainFragment?.setListOrder(result)

                        }
                    } else {
                        _isLoading.value = false
                        Log.e(TAG, "onFailure: ${response.message()}")
                        mainFragment?.showToast(response.message())
                    }
                }

                override fun onFailure(call: Call<PurchaseOrderResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure: ${t.message}")
                    mainFragment?.showToast(t.message.toString())
                }
            })
    }

    fun searchHeaderList(userName: String, passWord: String, id: String) {
        _isLoading.value = true
        ApiConfig(userName, passWord).getApiServiceHeader()
            .searchHeader(
                "documentNo='$id'and salesTransaction=false and documentStatus='CO'"
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
                        mainFragment?.showToast(response.message())
                    }
                }

                override fun onFailure(call: Call<PurchaseOrderResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure: ${t.message}")
                    mainFragment?.showToast(t.message.toString())
                }
            })
    }
}