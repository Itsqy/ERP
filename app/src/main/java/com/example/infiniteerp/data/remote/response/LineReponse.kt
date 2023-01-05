package com.example.infiniteerp.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class LineReponse(
    @field:SerializedName("response")
    val response: OrderLine,
)

@Parcelize
data class OrderLine(

    @field:SerializedName("data")
    val data: List<ListOrderLine>,

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
data class ListOrderLine(
    @field:SerializedName("lineNo")
    val lineNo: String?,

    @field:SerializedName("product\$_identifier")
    val product: String?,

    @field:SerializedName("orderedQuantity")
    val orderedQuantity: String?,

    @field:SerializedName("orderUOM")
    val orderUOM: String?,

    @field:SerializedName("uOM\$_identifier")
    val UOM: String?,

    @field:SerializedName("businessPartner\$_identifier")
    val bussinesPartner: String?,

    @field:SerializedName("lineNetAmount")
    val lineNetAmount: String?,

    @field:SerializedName("tax\$_identifier")
    val tax: String?,

    @field:SerializedName("unitPrice")
    val unitPrice: String?,

    @field:SerializedName("oezGrQuantity")
    val grQuantity: String?,

    @field:SerializedName("invoicedQuantity")
    val invoicedQuantity: String?,

    @field:SerializedName("scheduledDeliveryDate")
    val scheduledDeliveryDate: String?,
) : Parcelable


