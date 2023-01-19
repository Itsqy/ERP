package com.example.infiniteerp.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.infiniteerp.data.model.UserPreferences
import com.example.infiniteerp.dataStore
import com.example.infiniteerp.databinding.ActivityLoginBinding
import com.example.infiniteerp.home.HomeActivity
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
        setupUI(binding.layoutLogin)

        setUpEdt()


    }

    private fun setUpEdt() {
        binding.edtPass.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence, start: Int, count: Int, after: Int) {
//                    buttonListener()
                }

                override fun onTextChanged(p0: CharSequence, start: Int, before: Int, count: Int) {
                    buttonListener()
                }

                override fun afterTextChanged(p0: Editable) {
//                    buttonListener()
                }

            }
        )
    }

    fun setupUI(view: View) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener(OnTouchListener { v, event ->
                hideSoftKeyboard(this@LoginActivity)
                false
            })
        }
        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until (view as ViewGroup).childCount) {
                val innerView: View = (view as ViewGroup).getChildAt(i)
                setupUI(innerView)
            }
        }
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
    }


    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        ).get(LoginViewModel::class.java)
    }

    fun buttonListener() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val pass = binding.edtPass.text.toString().trim()
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
                    val intent = Intent(context, HomeActivity::class.java)
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


