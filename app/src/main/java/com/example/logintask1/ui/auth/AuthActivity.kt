package com.example.logintask1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.logintask1.databinding.ActivityAuthBinding
import com.example.logintask1.ui.auth.signin.SigninFragmentDirections
import com.example.logintask1.ui.auth.signup.SignupFragmentDirections
import com.google.android.material.tabs.TabLayout


class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.fragmentContainerView)
        binding.tabLayout.addOnTabSelectedListener(tabSelectListener)
    }
    private val tabSelectListener =  object: TabLayout.OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab?) {
            when (tab?.position) {
                1 -> {
                    val action = SigninFragmentDirections.actionSigninFragmentToSignupFragment()
//
                    navController.navigate(action)
//                    Log.d("pos", tab?.position.toString())
                }
                0 -> {
                    val action = SignupFragmentDirections.actionSignupFragmentToSigninFragment()
                    Log.d("pos", tab?.position.toString())
                    navController.navigate(action)
                }
            }

        }

        override fun onTabReselected(tab: TabLayout.Tab?) {

        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {

        }


    }


}