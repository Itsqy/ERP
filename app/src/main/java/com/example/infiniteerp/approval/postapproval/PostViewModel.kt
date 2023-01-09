package com.example.infiniteerp.approval.postapproval

import android.util.Log
import android.widget.Toast
import com.example.infiniteerp.data.remote.retrofit.ApiConfig
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class PostViewModel(var postActivity: PostModalActivtiy) {

    fun addPost(id: String) {
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

        val apiService = ApiConfig.getApiServiceHeader()
        apiService.postOrder("demo", "demo", requestBody)
            .enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        Log.d("TAG", "onResponse: $body")
                        Toast.makeText(postActivity, "Row is Updated ", Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            })


    }
}