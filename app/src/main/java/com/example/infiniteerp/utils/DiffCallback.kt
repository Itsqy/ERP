package com.example.infiniteerp.utils

import androidx.recyclerview.widget.DiffUtil

import com.example.infiniteerp.data.remote.response.ListOrder
import com.example.infiniteerp.data.remote.response.ListOrderLine

class DiffCallback(
    private val mOldFavList: ArrayList<ListOrder>,
    private val mNewFavList: List<ListOrder>
) : DiffUtil.Callback() {

    override fun getOldListSize() = mOldFavList.size

    override fun getNewListSize() = mNewFavList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        mOldFavList[oldItemPosition].id == mNewFavList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldFavList[oldItemPosition]
        val newEmployee = mNewFavList[newItemPosition]
        return oldEmployee.id == newEmployee.id
    }
}

class DiffCallbackLine(
    private val mOldFavList: ArrayList<ListOrderLine>,
    private val mNewFavList: List<ListOrderLine>
) : DiffUtil.Callback() {

    override fun getOldListSize() = mOldFavList.size

    override fun getNewListSize() = mNewFavList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        mOldFavList[oldItemPosition].lineNo == mNewFavList[newItemPosition].lineNo

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldFavList[oldItemPosition]
        val newEmployee = mNewFavList[newItemPosition]
        return oldEmployee.lineNo == newEmployee.lineNo
    }
}