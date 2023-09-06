package com.example.logintask1.ui.auth.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.logintask1.R
import com.example.logintask1.databinding.FragmentSigninBinding
import com.example.logintask1.ui.home.HomeActivity


class SigninFragment : Fragment() {
    private lateinit var binding: FragmentSigninBinding
    private val model: SigninViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false)
        with(binding) {
            viewModel = model
            lifecycleOwner = this@SigninFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtonListener()

    }
    private fun setupButtonListener(){
        binding.signinButton.setOnClickListener{
            if(model.validateFields()){
                startHomeActivity()
            }
        }

        binding.signinButtonGoogle.setOnClickListener {
           startHomeActivity()
        }
    }

    private fun startHomeActivity(){
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
    }

}

