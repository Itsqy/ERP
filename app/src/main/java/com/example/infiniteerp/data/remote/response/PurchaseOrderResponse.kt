package com.example.infiniteerp.data.remote.response

import android.os.Parcel
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

    @field:SerializedName("posted")
    val posted: String,

    @field:SerializedName("processed")
    val processed: String,
) : Parcelable


//test with parcelable
//the problem: when i appearing 'grandTotalAmount' its turn to boolean

//data class PurchaseOrderResponse(
//    @field:SerializedName("response")
//    val response: Order,
//)
//
//@Parcelize
//data class Order(
//
//    @field:SerializedName("data")
//    val data: List<ListOrder>,
//
//    @field:SerializedName("status")
//    val status: Int,
//
//    @field:SerializedName("totalRows")
//    val totalRows: Int,
//
//    @field:SerializedName("startRows")
//    val startRows: Int,
//
//    @field:SerializedName("endRows")
//    val endRows: Int,
//) : Parcelable
//
//
//data class ListOrder(
//    @field:SerializedName("id")
//    val id: String,
//
//    @field:SerializedName("documentNo")
//    val documentNo: String,
//
//    @field:SerializedName("orderDate")
//    val orderDate: String,
//
//    @field:SerializedName("scheduledDeliveryDate")
//    val scheduledDeliveryDate: String,
//
//    @field:SerializedName("documentStatus")
//    val documentStatus: String,
//
//    @field:SerializedName("businessPartner\$_identifier")
//    val bussinesPartner: String,
//
//    @field:SerializedName("grandTotalAmount")
//    val grandTotalAmount: String,
//
//    @field:SerializedName("posted")
//    val posted: String,
//
//    @field:SerializedName("processed")
//    val processed: String,
//) : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//    ) {
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(documentNo)
//        parcel.writeString(id)
//        parcel.writeString(orderDate)
//        parcel.writeString(grandTotalAmount)
//        parcel.writeString(posted)
//        parcel.writeString(scheduledDeliveryDate)
//        parcel.writeString(processed)
//        parcel.writeString(documentStatus)
//        parcel.writeString(bussinesPartner)
//    }
//
//    companion object CREATOR : Parcelable.Creator<ListOrder> {
//        override fun createFromParcel(parcel: Parcel): ListOrder {
//            return ListOrder(parcel)
//        }
//
//        override fun newArray(size: Int): Array<ListOrder?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
//
//
//
//
//
//







