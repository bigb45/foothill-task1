package com.example.logintask1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.logintask1.databinding.ActivityAuthBinding
import com.example.logintask1.ui.auth.signin.SigninFragmentDirections
import com.google.android.material.tabs.TabLayout


class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabLayout.addOnTabSelectedListener(tabSelectListener)
    }
    private val tabSelectListener =  object: TabLayout.OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab?) {
            when (tab?.position) {
                0 -> {
                    val directions = SigninFragmentDirections.actionSigninFragmentToHomeActivity()
//                    findNavController(R.id.).navigate(directions)
                }
                1 -> {
                    val directions = SigninFragmentDirections.actionSigninFragmentToHomeActivity()
//                    findNavController().navigate(directions)
                }
            }

        }

        override fun onTabReselected(tab: TabLayout.Tab?) {

        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {

        }


    }


}