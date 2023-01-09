package com.example.infiniteerp.approval.line

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.infiniteerp.data.remote.response.ListOrderLine
import com.example.infiniteerp.databinding.ActivityDetailLineBinding

class DetailLineActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailLineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tbLine)

        showDetail()

    }

    private fun showDetail() {

        val idHeader = intent.getParcelableExtra<ListOrderLine>("idLine")
        if (idHeader != null) {
            binding.tbLine.title = "Detail Line ${idHeader.lineNo}"
            val netAmount = idHeader.lineNetAmount
            binding.tvLine.text = idHeader.lineNo
            binding.tvProduct.text = idHeader.product
            binding.tvGrQty.text = idHeader.grQuantity
            binding.tvInvoiceQuantity.text = idHeader.invoicedQuantity
            binding.tvNetAmount.text = "Rp. $netAmount"
            binding.tvNetUnit.text = idHeader.unitPrice
            binding.tvOrderUom.text = idHeader.orderUOM
            binding.tvSdd.text = idHeader.scheduledDeliveryDate
            binding.tvTax.text = idHeader.tax
            binding.tvOrderedQuantity.text = idHeader.orderedQuantity
            binding.tvUom.text = idHeader.UOM
            binding.tvValue.text = idHeader.bussinesPartner


        } else {
            binding.tbLine.title = "tidak ada line"
            binding.tvLine.text = "tidak ada line"
            binding.tvProduct.text = "-"
            binding.tvGrQty.text = "-"
            binding.tvInvoiceQuantity.text = "-"
            binding.tvNetAmount.text = "-"
            binding.tvNetUnit.text = "-"
            binding.tvOrderUom.text = "-"
            binding.tvSdd.text = "-"
            binding.tvTax.text = "-"
            binding.tvOrderedQuantity.text = "-"
            binding.tvUom.text = "-"
            binding.tvValue.text = "-"

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}