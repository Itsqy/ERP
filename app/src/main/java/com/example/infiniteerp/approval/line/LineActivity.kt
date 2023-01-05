package com.example.infiniteerp.approval.line

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infiniteerp.data.remote.response.ListOrder
import com.example.infiniteerp.data.remote.response.ListOrderLine
import com.example.infiniteerp.databinding.ActivityLineBinding

class LineActivity : AppCompatActivity() {
    lateinit var binding: ActivityLineBinding
    private lateinit var adapter: LineAdapter
    lateinit var lineViewModel: LineViewModel

    companion object {
        const val EXTRA_LINE = "line_extra"
        const val TAG = "LineActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLineBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.tbLine)
        binding.tbLine.title = "Lines"
        val idHeader =
            intent.getParcelableExtra<ListOrder>("idLine")
        lineViewModel = LineViewModel(this)
        if (idHeader != null) {
            lineViewModel.showListLine(idHeader.id)
        }

    }


    fun setListOrderLine(dataOrder: List<ListOrderLine>) {

        val adapter = LineAdapter(dataOrder, this)
        binding.apply {
            rvLine.setHasFixedSize(true)
            rvLine.layoutManager = LinearLayoutManager(this@LineActivity)
            rvLine.adapter = adapter
        }
    }
    fun showLoading() {
        lineViewModel.isLoading.observe(this) {
            binding?.apply {
                if (it) {

                }
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



