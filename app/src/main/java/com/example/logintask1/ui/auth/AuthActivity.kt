package com.example.logintask1.ui.auth

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.logintask1.R
import com.example.logintask1.databinding.ActivityAuthBinding
import com.example.logintask1.ui.auth.signin.SigninFragment
import com.example.logintask1.ui.auth.signup.SignupFragment
import com.google.android.material.tabs.TabLayoutMediator

private const val PAGE_COUNT = 2


class AuthActivity : FragmentActivity() {
    private lateinit var binding: ActivityAuthBinding

//        private lateinit var navController: NavController
    private lateinit var pager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        initView()
    }


    private fun initView() {
//        navController = findNavController(R.id.)
        with(binding) {
//            tabLayout.addOnTabSelectedListener(tabSelectListener)
            this@AuthActivity.pager = authPager
        }
        pager.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Sign in"
                1 -> "Sign up"
                else -> {""}
            }

            }.attach()

    }


    private inner class PagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return PAGE_COUNT
        }

        override fun createFragment(position: Int): Fragment {
            val fragment = when (position) {
                1 -> SignupFragment()

                0 -> SigninFragment()
                else -> {
                    throw NotFoundException("Cannot find fragment at position $position")
                }
            }
            return fragment
        }

    }


}