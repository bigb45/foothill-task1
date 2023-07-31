package com.example.logintask1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.logintask1.databinding.ActivitySignupBinding
import com.google.android.material.textfield.TextInputLayout

class SignupActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signinText = binding.signinText
        val signupButton = binding.signinButton
        val signupButtonGoogle = binding.signinButtonGoogle

        val emailContainer = binding.emailContainer
        val passwordContainer = binding.signupPasswordContainer
        val emailAddressEditText = binding.emailEditText
        val passwordEditText = binding.passwordEditText
        val confirmPasswordEditText = binding.confirmPasswordEditText



        signinText.setOnClickListener {
            Log.d("Debug", "signing in with existing account")
            val signinIntent = Intent(this, SigninActivity::class.java)
            startActivity(signinIntent)
        }

        signupButton.setOnClickListener {
            val emailAddress = emailAddressEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            Log.d("validating", "validating email address $emailAddress")
            val isEmailValid = validateEmail(emailAddress, emailContainer)
            val isPasswordValid = validatePassword(password, confirmPassword, passwordContainer)
            if(isEmailValid && isPasswordValid){
                val mainPageIntent = Intent(this, MainActivity::class.java)
                startActivity(mainPageIntent)
            }
        }

    }

    private fun validateEmail(email: String, emailContainer: TextInputLayout): Boolean{
        val emailRegex = Regex(".+@.+[.com]$")
        if(!emailRegex.matches(email)){
            Log.d("email", "$email does not match the pattern!")
            emailContainer.helperText = "please enter a correct email"
            return false
        }else{
            emailContainer.helperText = ""

        }
        return true
    }

    private fun validatePassword(password: String, confirmPassword: String, passwordContainer: TextInputLayout): Boolean{
        if(password.length < 8){

            Log.d("password", "password length is less than 8 characters")
            passwordContainer.helperText = "password must have 8 or more characters"
            return false
        }else{
            passwordContainer.helperText = ""

        }
        if(password != confirmPassword){
            Log.d("password", "passwords don't match")
            passwordContainer.helperText = "passwords don't match"
            return false
        }else{
            passwordContainer.helperText = ""
        }
        return true
    }

}