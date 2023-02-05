package com.example.infiniteerp.approval.postapproval

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.infiniteerp.approval.DetailApprovalActivity
import com.example.infiniteerp.approval.release.ReleaseViewModel
import com.example.infiniteerp.data.remote.response.PurchaseOrderResponse
import com.example.infiniteerp.data.remote.response.ResponseApprove
import com.example.infiniteerp.data.remote.retrofit.ApiConfig
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel(var postActivity: DetailApprovalActivity) {


    fun addPost(userName: String, passWord: String, id: String) {
        val jsonObject = JSONObject()
        val jsonArray = JSONArray()

        jsonObject.put("DocRoutingStepId", null)
        jsonObject.put("recordIdList", jsonArray.put(id))
        jsonObject.put("action", "CO")
        jsonObject.put("adTabId", "294")
        jsonObject.put("windowId", "181")
        jsonObject.put("doc_status_from", "")

        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val apiService = ApiConfig(userName, passWord).getApiServiceHeader()
        apiService.postOrder(requestBody)
            .enqueue(object : Callback<ResponseApprove> {
                override fun onResponse(
                    call: Call<ResponseApprove>,
                    response: Response<ResponseApprove>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()!!.message
                        if (body.severity != "error") {
                            Log.d("TAG", "onResponse: ${body.text}")
                            postActivity.ShowToast(body.text)
                        } else {
                            Log.d("TAG", "onResponse: ${body.severity}")
                            postActivity.ShowToast(body.severity)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseApprove>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message.toString()}")
                    postActivity.ShowToast(t.message.toString())
                }
            })


    }


}