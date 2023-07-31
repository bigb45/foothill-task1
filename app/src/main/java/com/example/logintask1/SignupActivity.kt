package com.example.logintask1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.logintask1.databinding.ActivitySignupBinding

class SignupActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signinText = binding.signinText

        signinText.setOnClickListener {
            Log.d("Debug", "signing in with existing account")
            val signinIntent = Intent(this, SigninActivity::class.java)
            startActivity(signinIntent)
        }


    }

    private fun validate(email: String, password: String, confirmPassword: String){
        val emailRegex = Regex(".+@.+[.com]$")
        if(!emailRegex.matches(email)){
            Log.d("email", "$email does not match the pattern!")
            return
        }
        if(password.length < 8){

            Log.d("password", "password length is less than 8 characters")
            return
        }
        if(password != confirmPassword){
            Log.d("password", "passwords don't match")
            return
        }

    }

}