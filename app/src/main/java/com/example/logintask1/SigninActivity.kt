package com.example.logintask1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import com.example.logintask1.databinding.ActivitySigninBinding
import com.google.android.material.textfield.TextInputLayout


class SigninActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val createAccountText = binding.signinText
        val signinButton = binding.signinButton


        val text = createAccountText.text
        val spannable = SpannableString(text)
        spannable.setSpan(UnderlineSpan(), 0, text.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        createAccountText.text = spannable
        emailFocusListener()
        passwordFocusListener()

        signinButton.setOnClickListener {
            val isPasswordValid = (binding.emailContainer.helperText == null)
            val isEmailValid = (binding.passwordContainer.helperText == null)
            if(isEmailValid && isPasswordValid){
                val mainPageIntent = Intent(this, MainActivity::class.java)
                startActivity(mainPageIntent)
            }
        }
        createAccountText.setOnClickListener {
            Log.d("Debug", "creating new account")
            val signupIntent = Intent(this, SignupActivity::class.java)
            startActivity(signupIntent)
        }

    }

    private fun emailFocusListener(){
        val container = binding.emailContainer
        binding.emailEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun afterTextChanged(newText: Editable?) {
                container.helperText = validateEmail()
            }
        })

    }

    private fun passwordFocusListener(){
        val container = binding.passwordContainer
        binding.passwordEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun afterTextChanged(newText: Editable?) {
                container.helperText = validatePassword()
            }
        })


    }

    private fun validateEmail(): String?{

        val emailRegex = Regex(".+@.+[.com]$")
        val email = binding.emailEditText.text.toString()

        if(!emailRegex.matches(email)){
            Log.d("match", "$email matches the pattern!")
            return "incorrect email pattern"
        }
        return null
    }

    private fun validatePassword(): String?{
        val password = binding.passwordEditText.text.toString()
        if(password.length < 8){
            return "password must be at least 8 characters"
        }
        return null

    }
}

