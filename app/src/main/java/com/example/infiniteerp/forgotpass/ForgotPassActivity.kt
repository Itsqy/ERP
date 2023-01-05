package com.example.infiniteerp.forgotpass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.infiniteerp.databinding.ActivityForgotPassBinding

class ForgotPassActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityForgotPassBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)


    }
}