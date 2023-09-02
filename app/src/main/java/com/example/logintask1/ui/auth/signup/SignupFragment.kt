package com.example.logintask1.ui.auth.signup

import android.content.Intent
import android.os.Bundle

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.logintask1.R
import com.example.logintask1.databinding.FragmentSignupBinding
import com.example.logintask1.ui.auth.signin.SigninFragmentDirections
import com.example.logintask1.ui.home.HomeActivity


class SignupFragment : Fragment(R.layout.fragment_signup) {
    private lateinit var binding: FragmentSignupBinding
    private val model: SignupViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignupBinding.bind(view)
        with(binding) {
            viewModel = model
            lifecycleOwner = this@SignupFragment
        }
        setupButtonListener()
    }

    private fun setupButtonListener() {
        binding.signinButton.setOnClickListener {
            if(model.validateFields()){
                startHomeActivity()
            }
        }
    }

    private fun startHomeActivity() {
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
    }


}
