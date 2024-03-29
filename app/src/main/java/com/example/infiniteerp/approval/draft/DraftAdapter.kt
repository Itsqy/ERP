package com.example.infiniteerp.approval.draft

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.infiniteerp.R
import com.example.infiniteerp.approval.DetailApprovalActivity
import com.example.infiniteerp.data.remote.response.ListOrder

class DraftAdapter(
    private val dataHeader: List<ListOrder>?
) :
    RecyclerView.Adapter<DraftAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleDocs = view.findViewById<TextView>(R.id.tv_title_docs_draft)
        val bussinessPartner = view.findViewById<TextView>(R.id.tv_bussinespartner_draft)
        val docNo = view.findViewById<TextView>(R.id.tv_document_no_draft)
        val totalNet = view.findViewById<TextView>(R.id.tv_totalnet_draft)
        val btnHeader = view.findViewById<Button>(R.id.btn_header)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.item_row_draft, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val count = position + 1
        holder.titleDocs.text = "Document ${count}"
        holder.docNo.text = dataHeader?.get(position)?.documentNo
        holder.bussinessPartner.text = dataHeader?.get(position)?.bussinesPartner
        holder.totalNet.text = "Rp.  ${dataHeader?.get(position)?.grandTotalAmount}"
        holder.btnHeader.setOnClickListener {
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

