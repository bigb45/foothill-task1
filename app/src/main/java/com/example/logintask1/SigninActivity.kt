package com.example.logintask1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import com.example.logintask1.databinding.ActivitySigninBinding


class SigninActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val createAccountText = findViewById<TextView>(R.id.signinText)
//        val emailAddressEditText = findViewById<EditText>(R.id.emailEditText)
//        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
//        val signinButton = findViewById<TextView>(R.id.signinButton)
//        val signinButtonGoogle = findViewById<TextView>(R.id.signinButtonGoogle)

        /* use binding, much cleaner */
        val createAccountText = binding.signinText
        val emailAddressEditText = binding.emailEditText
        val passwordEditText = binding.passwordEditText
        val signinButton = binding.signinButton
        val signinButtonGoogle = binding.signinButtonGoogle

        val text = createAccountText.text
        val spannable = SpannableString(text)
        spannable.setSpan(UnderlineSpan(), 0, text.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        createAccountText.text = spannable

        signinButton.setOnClickListener {

            val password = passwordEditText.text.toString()
            val emailAddress = emailAddressEditText.text.toString()
            Log.d("validating", "validating email address $emailAddress")
            validateEmail(emailAddress)
        }
//        Log.d("regex matched", regex.containsMatchIn("bgd4500@gmail.com").toString())
        createAccountText.setOnClickListener {
            Log.d("Debug", "creating new account")
            val signupIntent = Intent(this, SignupActivity::class.java)
            startActivity(signupIntent)
        }



    }

    private fun validateEmail(email: String){
        val emailRegex = Regex(".+@.+[.com]$")
        if(emailRegex.matches(email)){
            Log.d("match", "$email matches the pattern!")
        }

    }
}