package com.example.logintask1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.logintask1.databinding.ActivityMainBinding
import com.example.logintask1.signinPackage.SigninFragment
import com.example.logintask1.signupPackage.SignupFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignin.setOnClickListener {
            replaceFragment(SigninFragment())
        }

        binding.buttonSignup.setOnClickListener {
            replaceFragment(SignupFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}