package com.example.infiniteerp.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PurchaseOrderResponse(
    @field:SerializedName("response")
    val response: Order,
)

@Parcelize
data class Order(

    @field:SerializedName("data")
    val data: List<ListOrder>,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("totalRows")
    val totalRows: Int,

    @field:SerializedName("startRows")
    val startRows: Int,

    @field:SerializedName("endRows")
    val endRows: Int,
) : Parcelable


@Parcelize
data class ListOrder(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("documentNo")
    val documentNo: String,

    @field:SerializedName("orderDate")
    val orderDate: String,

    @field:SerializedName("scheduledDeliveryDate")
    val scheduledDeliveryDate: String,

    @field:SerializedName("documentStatus")
    val documentStatus: String,

    @field:SerializedName("businessPartner\$_identifier")
    val bussinesPartner: String,

    @field:SerializedName("grandTotalAmount")
    val grandTotalAmount: String,
) : Parcelable




