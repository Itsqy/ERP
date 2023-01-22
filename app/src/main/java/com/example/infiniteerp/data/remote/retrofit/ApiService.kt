package com.example.infiniteerp.data.remote.retrofit

import com.example.infiniteerp.data.remote.response.LineReponse
import com.example.infiniteerp.data.remote.response.LoginResponse
import com.example.infiniteerp.data.remote.response.PurchaseOrderResponse
import com.example.infiniteerp.data.remote.response.ResponseApprove
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("auth")
    fun login(
        @Field("username") email: String,
        @Field("password") pass: String,
        @Field("clientId") clientId: String = "62704287CEB744C7BA2CF4E4E39335D7",
        @Field("orgId") orgId: String = "*",
    ): Call<LoginResponse>

    @GET("org.openbravo.service.json.jsonrest/Order")
    fun searchHeader(
        @Query("_where") params: String,
    ): Call<PurchaseOrderResponse>


    @GET("org.openbravo.service.json.jsonrest/Order")
    fun getHeader(
        @Query("_where") params: String,
    ): Call<PurchaseOrderResponse>

    @GET("org.openbravo.service.json.jsonrest/OrderLine?")
    fun getOrderLine(
        @Query("_where") id: String,
    ): Call<LineReponse>

    //    make a paramter raw json
    @Headers("Content-Type: application/json")
    @POST("org.openbravo.client.kernel?_action=org.wirabumi.gen.oez.event.DocumentRoutingHandler")
    fun postOrder(
        @Body requestBody: RequestBody
    ): Call<ResponseApprove>


}

