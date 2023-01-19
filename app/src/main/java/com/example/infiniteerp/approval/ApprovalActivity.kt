package com.example.infiniteerp.approval

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.infiniteerp.approval.draft.DraftFragment
import com.example.infiniteerp.approval.release.ReleaseFragment
import com.example.infiniteerp.data.model.UserModel
import com.example.infiniteerp.data.model.UserPreferences

import com.example.infiniteerp.databinding.ActivityApprovalBinding
import com.example.infiniteerp.utils.ViewModelFactory

class ApprovalActivity : AppCompatActivity() {
    private lateinit var approvalViewModel: ApprovalViewModel
    private lateinit var user: UserModel
    private lateinit var binding: ActivityApprovalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApprovalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setSupportActionBar(binding.tbApproval)
        setUpTabs()
//        setupViewModel()
    }

//    private fun setupViewModel() {
//        approvalViewModel = ViewModelProvider(
//            this@ApprovalActivity,
//            ViewModelFactory(UserPreferences.getInstance())
//        )[ApprovalViewModel::class.java]
//
//
//    }


    private fun setUpTabs() {
        val adapter = ApprovalAdapter(supportFragmentManager)
        adapter.addFragment(DraftFragment(), "Draft")
        adapter.addFragment(ReleaseFragment(), "Complete")
        binding.viewPager.adapter = adapter

        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


}