package com.example.infiniteerp.approval.line

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infiniteerp.approval.ApprovalViewModel
import com.example.infiniteerp.data.model.UserModel
import com.example.infiniteerp.data.model.UserPreferences
import com.example.infiniteerp.data.remote.response.ListOrder
import com.example.infiniteerp.data.remote.response.ListOrderLine
import com.example.infiniteerp.databinding.ActivityLineBinding
import com.example.infiniteerp.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class LineActivity : AppCompatActivity() {
    lateinit var binding: ActivityLineBinding
    lateinit var lineViewModel: LineViewModel
    private lateinit var approvalViewModel: ApprovalViewModel
    private lateinit var user: UserModel


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLineBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.tbLine)


        binding.tbLine.title = "Lines"

        seUpViewModel()
        showLoading()


    }

    private fun seUpViewModel() {
        val idHeader = intent.getParcelableExtra<ListOrder>("idLine")
        lineViewModel = LineViewModel(this)
        if (idHeader != null) {
            approvalViewModel =
                ViewModelProvider(
                    this@LineActivity, ViewModelFactory(UserPreferences.getInstance(dataStore))
                )[ApprovalViewModel::class.java]

            approvalViewModel.getUser().observe(this) {
                user = UserModel(
                    it.username, it.password, it.clientId, it.orgId, it.token, true
                )
                lineViewModel.showListLine(user.username, user.password, idHeader.id)
            }

//            Log.d("lineActivity", "$user")


            showLoading()

        }
    }

    fun showEmpty() {
        binding.rvLine.visibility = View.GONE
        binding.tvEmpty.visibility = View.VISIBLE
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
                    pbLine.visibility = View.VISIBLE
                    rvLine.visibility = View.INVISIBLE
                } else {
                    pbLine.visibility = View.GONE
                    rvLine.visibility = View.VISIBLE
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



