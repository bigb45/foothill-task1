package com.example.logintask1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.logintask1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragmentContainerView)
        binding.buttonSignin.setOnClickListener {
            if(navController.currentDestination?.id != R.id.signinFragment) {
                val action = R.id.action_signupFragment_to_signinFragment
                navController.navigate(action)
            }
        }

        binding.buttonSignup.setOnClickListener {
            if(navController.currentDestination?.id != R.id.signupFragment) {
                val action = R.id.action_signinFragment_to_signupFragment
                navController.navigate(action)
            }

        }
    }

}