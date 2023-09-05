package com.example.logintask1.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.logintask1.R
import com.example.logintask1.databinding.ActivityAuthBinding
import com.example.logintask1.ui.auth.signin.SigninFragmentDirections
import com.example.logintask1.ui.auth.signup.SignupFragmentDirections
import com.google.android.material.tabs.TabLayout


class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        initView()
    }


    private fun initView(){
        navController = findNavController(R.id.fragmentContainerView)
        binding.tabLayout.addOnTabSelectedListener(tabSelectListener)
    }

    //TODO you need to integrate the tab layout directly with navController
    // use viewPager 2


    private val tabSelectListener =  object: TabLayout.OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab?) {
            when (tab?.position) {
                1 -> {
                    val action = SigninFragmentDirections.actionSigninFragmentToSignupFragment()
                    navController.navigate(action)
                }
                0 -> {
                    val action = SignupFragmentDirections.actionSignupFragmentToSigninFragment()
                    navController.navigate(action)
                }
            }

        }

        override fun onTabReselected(tab: TabLayout.Tab?) {}

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

    }


}