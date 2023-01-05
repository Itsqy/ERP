package com.example.infiniteerp.approval.release

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.infiniteerp.R
import com.example.infiniteerp.approval.DetailApprovalActivity
import com.example.infiniteerp.data.remote.response.ListOrder

class ReleaseAdapter(private val dataHeader: List<ListOrder>?) :
    RecyclerView.Adapter<ReleaseAdapter.MyViewHolder>() {


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bussinessPartner = view.findViewById<TextView>(R.id.tv_bussinespartner_release)
        val docNo = view.findViewById<TextView>(R.id.tv_document_release)
        val totalNet = view.findViewById<TextView>(R.id.tv_totalnet_release)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.item_row_release, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.docNo.text = dataHeader?.get(position)?.id
        holder.bussinessPartner.text = dataHeader?.get(position)?.bussinesPartner
        holder.totalNet.text = dataHeader?.get(position)?.grandTotalAmount
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailApprovalActivity::class.java)
            intent.putExtra("idHeader", dataHeader?.get(position))
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        if (dataHeader != null) {
            return dataHeader.size
        }
        return 0
    }
}