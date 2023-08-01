package com.example.logintask1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
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
        val emailAddressEditText = binding.emailEditText
        val passwordEditText = binding.passwordEditText
        val signinButton = binding.signinButton
        val emailContainer = binding.emailContainer
        val passwordContainer = binding.passwordContainer

        val text = createAccountText.text
        val spannable = SpannableString(text)
        spannable.setSpan(UnderlineSpan(), 0, text.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        createAccountText.text = spannable

        signinButton.setOnClickListener {

            val password = passwordEditText.text.toString()
            val emailAddress = emailAddressEditText.text.toString()
            Log.d("validating", "validating email address $emailAddress")
            val isEmailValid = validateEmail(emailAddress, emailContainer)
            val isPasswordValid = validatePassword(password, passwordContainer)
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

    private fun validateEmail(email: String, emailContainer: TextInputLayout): Boolean{
        val emailRegex = Regex(".+@.+[.com]$")
        if(!emailRegex.matches(email)){
            Log.d("match", "$email matches the pattern!")
            emailContainer.helperText = "incorrect email pattern"
            return false
        }else{
            emailContainer.helperText = ""

        }
        return true
    }

    private fun validatePassword(password: String, passwordContainer: TextInputLayout): Boolean{
        if(password == ""){
            Log.d("password", "please provide a password")
            passwordContainer.helperText = "please provide a password"
            return false
        }else{
            passwordContainer.helperText = ""
        }
        return true

    }
}

