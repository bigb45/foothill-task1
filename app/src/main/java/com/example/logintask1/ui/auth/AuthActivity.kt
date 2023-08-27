package com.example.logintask1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.logintask1.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragmentContainerView)
        setupButtonListeners(navController)

    }

    private fun setupButtonListeners(navController: NavController) {
        // this should be replaced with a bottomNavBar and a setupWithBottomNavBar func

        binding.buttonSignin.setOnClickListener {

            if (navController.currentDestination?.id != R.id.signinFragment) {
                val action = R.id.action_signupFragment_to_signinFragment
                navController.navigate(action)
            }
        }

        binding.buttonSignup.setOnClickListener {

            if (navController.currentDestination?.id != R.id.signupFragment) {
                val action = R.id.action_signinFragment_to_signupFragment
                navController.navigate(action)
            }
        }


    }

}