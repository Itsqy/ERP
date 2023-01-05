package com.example.infiniteerp.data.remote.retrofit

import com.example.infiniteerp.data.remote.response.LineReponse
import com.example.infiniteerp.data.remote.response.LoginResponse
import com.example.infiniteerp.data.remote.response.PurchaseOrderResponse
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

    @GET("Order")
    fun getAllOrderDraft(
        @Header("Username") username: String = "demo",
        @Header("Password") password: String = "demo",
        @Query("_where") id: String,

        ): Call<PurchaseOrderResponse>

    @GET("Order")
    fun getAllOrderRelease(
        @Header("Username") username: String = "demo",
        @Header("Password") password: String = "demo",
        @Query("_where") id: String,

        ): Call<PurchaseOrderResponse>

    @GET("OrderLine?")
    fun getOrderLine(
        @Header("Username") username: String = "demo",
        @Header("Password") password: String = "demo",
        @Query("_where") id: String,
    ): Call<LineReponse>

//    make a paramter raw json


}