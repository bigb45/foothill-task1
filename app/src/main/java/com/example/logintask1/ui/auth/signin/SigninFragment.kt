package com.example.logintask1.ui.auth.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.logintask1.R
import com.example.logintask1.databinding.FragmentSigninBinding


class SigninFragment : Fragment(R.layout.fragment_signin) {
    private lateinit var binding: FragmentSigninBinding
    private val model: SigninViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSigninBinding.bind(view)
        with(binding) {
            viewModel = model
            lifecycleOwner = this@SigninFragment
        }

    }


}

