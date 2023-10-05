package com.example.logintask1.ui.auth.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.logintask1.R
import com.example.logintask1.databinding.FragmentSigninBinding
import com.example.logintask1.ui.auth.signup.SignupViewModel
import com.example.logintask1.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SigninFragment : Fragment() {
    private lateinit var binding: FragmentSigninBinding
    private val viewModel: SigninViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        with(binding) {
            viewModel = this@SigninFragment.viewModel
            lifecycleOwner = this@SigninFragment
        }
        setupButtonListener()
    }

    private fun setupButtonListener() {
        binding.signinButton.setOnClickListener {
            if (viewModel.validateFields()) {
                startHomeActivity()
            }
        }

        binding.signinButtonGoogle.setOnClickListener {
            startHomeActivity()
        }
    }

    private fun startHomeActivity() {
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
    }

}

