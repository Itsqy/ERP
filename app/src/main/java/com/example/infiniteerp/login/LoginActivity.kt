package com.example.infiniteerp.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.infiniteerp.approval.ApprovalActivity
import com.example.infiniteerp.data.model.UserPreferences
import com.example.infiniteerp.dataStore
import com.example.infiniteerp.databinding.ActivityLoginBinding
import com.example.infiniteerp.utils.Helpers
import com.example.infiniteerp.utils.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        buttonListener()
        setupViewModel()

    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        ).get(LoginViewModel::class.java)
    }

    fun buttonListener() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPass.text.toString()
            if (email.isEmpty() || pass.isEmpty()) {
                Helpers.showDialog(this, "Please fill all the fields")
            } else {
                loginViewModel.login(email, pass, object : Helpers.ApiCallbackString {
                    override fun onResponse(success: Boolean, message: String) {
                        showAlertDialog(success, message)
                    }




                })
            }


        }

    }

    fun showAlertDialog(param: Boolean, message: String) {
        if (param) {
            AlertDialog.Builder(this).apply {
                setTitle("Information")
                setMessage("Login success")
                setPositiveButton("Continue") { _, _ ->
                    val intent = Intent(context, ApprovalActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                create()
                show()
            }
        } else {
            AlertDialog.Builder(this).apply {
                setTitle("information")
                setMessage("Login Failed" + ", $message")
                setPositiveButton("Close") { _, _ ->

                }
                create()
                show()

            }
        }
    }
}


