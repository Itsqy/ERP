package com.example.infiniteerp.approval

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.infiniteerp.approval.line.LineActivity
import com.example.infiniteerp.approval.postapproval.PostViewModel
import com.example.infiniteerp.data.model.UserModel
import com.example.infiniteerp.data.model.UserPreferences
import com.example.infiniteerp.data.remote.response.ListOrder
import com.example.infiniteerp.databinding.ActivityDetailApprovalBinding
import com.example.infiniteerp.utils.ViewModelFactory


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class DetailApprovalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailApprovalBinding
    private lateinit var postViewModel: PostViewModel
    private lateinit var approvalViewModel: ApprovalViewModel
    private lateinit var user: UserModel

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

            if (releaseOrder.documentStatus != "CO") {
                binding.bnApprove.visibility = View.VISIBLE
                binding.bnDetail.setOnClickListener {
                    val intent = Intent(this, LineActivity::class.java)
                    intent.putExtra("idLine", releaseOrder)
                    startActivity(intent)
                }
            } else {

                binding.bnApprove.visibility = View.GONE

            }
            binding.bnApprove.setOnClickListener {

                approvalViewModel =
                    ViewModelProvider(
                        this@DetailApprovalActivity,
                        ViewModelFactory(UserPreferences.getInstance(dataStore))
                    )[ApprovalViewModel::class.java]


                postViewModel = PostViewModel(this)
                if (releaseOrder != null) {
                    if (releaseOrder.grandTotalAmount != "0") {
                        approvalViewModel.getUser().observe(this) {
                            user = UserModel(
                                it.username, it.password, it.clientId, it.orgId, it.token, true
                            )
                            postViewModel.addPost(user.username, user.password, releaseOrder.id)
                        }

                        Toast.makeText(this, "1 row updated complete", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Header ini tidak memiliki Lines ", Toast.LENGTH_SHORT)
                            .show()
                    }
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