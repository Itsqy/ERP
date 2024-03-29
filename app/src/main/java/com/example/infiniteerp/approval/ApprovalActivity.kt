package com.example.infiniteerp.approval

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setSupportActionBar(binding.tbApproval)
        setUpTabs()

    }



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