package com.example.logintask1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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


        val passwordContainer = binding.signupPasswordContainer
        val passwordEditText = binding.passwordEditText
        val confirmPasswordEditText = binding.confirmPasswordEditText


        signinText.setOnClickListener {
            Log.d("Debug", "signing in with existing account")
            val signinIntent = Intent(this, SigninActivity::class.java)
            startActivity(signinIntent)
        }
        emailChangeListener()
        passwordChangeListener()
        signupButton.setOnClickListener {
            val isEmailValid = binding.emailContainer.helperText == null
            val isPasswordValid = binding.signupPasswordContainer.helperText == null
            if(isEmailValid && isPasswordValid){
                val mainPageIntent = Intent(this, MainActivity::class.java)
                startActivity(mainPageIntent)
            }
        }

    }

    private fun emailChangeListener(){
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

    private fun passwordChangeListener(){

        val container = binding.signupPasswordContainer
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

        binding.confirmPasswordEditText.addTextChangedListener(object: TextWatcher {
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
        val email = binding.emailEditText.text.toString()
        val emailRegex = Regex(".+@.+[.com]$")
        if(!emailRegex.matches(email)){
            Log.d("email", "$email does not match the pattern!")
            return "please enter a correct email"
        }
        return null
    }

    private fun validatePassword(): String?{
        val confirmPassword = binding.confirmPasswordEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if(password.length < 8){
            return "password must have 8 or more characters"
        }

        if(password != confirmPassword){
            Log.d("$password", "$confirmPassword")
            return "passwords don't match"

        }
        return null
    }

}