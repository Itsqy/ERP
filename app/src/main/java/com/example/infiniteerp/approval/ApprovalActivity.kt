package com.example.infiniteerp.approval

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.infiniteerp.approval.draft.DraftFragment
import com.example.infiniteerp.approval.release.ReleaseFragment
import com.example.infiniteerp.data.model.UserModel
import com.example.infiniteerp.data.model.UserPreferences
import com.example.infiniteerp.dataStore
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
        setUpTabs()
        setupViewModel()


    }

    private fun setupViewModel() {
        approvalViewModel = ViewModelProvider(
            this@ApprovalActivity,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[ApprovalViewModel::class.java]


    }

    private fun setUpTabs() {
        val adapter = ApprovalAdapter(supportFragmentManager)
        adapter.addFragment(DraftFragment(), "Draft")
        adapter.addFragment(ReleaseFragment(), "Complete")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }


}