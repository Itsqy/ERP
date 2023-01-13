package com.example.infiniteerp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.infiniteerp.approval.ApprovalViewModel
import com.example.infiniteerp.data.model.UserPreferences
import com.example.infiniteerp.home.HomeActivity
import com.example.infiniteerp.login.LoginActivity
import com.example.infiniteerp.utils.ViewModelFactory

class SplashScreen : AppCompatActivity() {
    private lateinit var approvalViewModel: ApprovalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar?.hide()
        setupViewModel()


    }

    private fun setupViewModel() {
        Thread(Runnable {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            runOnUiThread {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }).start()
    }


    private fun setupViewModelWthUserPref() {
        approvalViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[ApprovalViewModel::class.java]

        approvalViewModel.getUser().observe(this) {
            if (it.isLogin) {
                Thread(Runnable {
                    try {
                        Thread.sleep(2000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    runOnUiThread {
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                }).start()

            } else {
                Thread(Runnable {
                    try {
                        Thread.sleep(2000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    runOnUiThread {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }).start()
            }
        }
    }


}
