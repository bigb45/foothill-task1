package com.example.logintask1.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.logintask1.R
import com.example.logintask1.databinding.FragmentSignupBinding
import com.example.logintask1.ui.home.HomeActivity


class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private val model: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        with(binding) {
            viewModel = model
            lifecycleOwner = this@SignupFragment
        }
        return binding.root
    }
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
