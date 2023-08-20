package com.example.logintask1.signupPackage

import android.os.Bundle

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.logintask1.R
import com.example.logintask1.databinding.FragmentSignupBinding



class SignupFragment: Fragment(R.layout.fragment_signup) {
    private lateinit var binding: FragmentSignupBinding
    private val model: SignupViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignupBinding.bind(view)
        binding.viewModel = model
        binding.lifecycleOwner = this

    }

}
