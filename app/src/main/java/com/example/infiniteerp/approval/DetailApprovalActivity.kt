package com.example.infiniteerp.approval

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.infiniteerp.approval.line.LineActivity
import com.example.infiniteerp.approval.postapproval.PostModalActivtiy
import com.example.infiniteerp.data.remote.response.ListOrder
import com.example.infiniteerp.databinding.ActivityDetailApprovalBinding


class DetailApprovalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailApprovalBinding

    companion object {
        const val EXTRA_RELEASE_ORDER = "release_order"
        const val EXTRA_LINE = "line_extra"
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailApprovalBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        setSupportActionBar(binding.tbApproval)
        showDetail()


    }

    private fun showDetail() {
        val releaseOrder =
            intent.getParcelableExtra<ListOrder>("idHeader")


        if (releaseOrder != null) {
            val netAmount = releaseOrder.grandTotalAmount
            binding.tvDocumentRelease.text = releaseOrder.documentNo
            binding.tbApproval.title = "Detail Header ${releaseOrder.documentNo}"
            binding.tvOrderDate.text = releaseOrder.orderDate
            binding.tvBusinessPartner.text = releaseOrder.bussinesPartner
            binding.tvDeliveryDate.text = releaseOrder.scheduledDeliveryDate
            binding.tvTotalNet.text = "Rp. $netAmount"
            binding.tvDocStatus.text = releaseOrder.documentStatus
            binding.bnDetail.text = releaseOrder.id
            binding.bnDetail.setOnClickListener {
                val intent = Intent(this, LineActivity::class.java)
                intent.putExtra("idLine", releaseOrder)
                startActivity(intent)
            }

            binding.bnApprove.setOnClickListener {

                val intent = Intent(this, PostModalActivtiy::class.java)
                intent.putExtra("popuptitle", "Process Request")
                intent.putExtra("popuptext", "Action :")
                intent.putExtra("popupbtn", "OK")
                intent.putExtra("darkstatusbar", false)
                intent.putExtra("idheader", releaseOrder)
                startActivity(intent)
            }


        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}