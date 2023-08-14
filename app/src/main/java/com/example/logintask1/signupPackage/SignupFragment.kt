package com.example.logintask1.signupPackage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.logintask1.R
import com.example.logintask1.databinding.FragmentSignupBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.reflect.KFunction1


class SignupFragment: Fragment(R.layout.fragment_signup) {
    private lateinit var binding: FragmentSignupBinding
    private val model: SignupViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignupBinding.bind(view)
        binding.viewModel = model
        binding.lifecycleOwner = this

//        val signupButton = binding.signinButton
//        signupButton.setOnClickListener {
//            val isEmailValid = binding.emailContainer.helperText == null
//            val isPasswordValid = binding.signupPasswordContainer.helperText == null
//            if(isEmailValid && isPasswordValid){
//                Log.d("Debug", "signing in with existing account")
//            }
//
//        binding.nameEditText.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                // do nothing
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                // do nothing
//            }
//
//            override fun afterTextChanged(newText: Editable?) {
//            }
//        })
//
//
//        observe(binding.emailContainer, model.emailError)
//        observe(binding.signupPasswordContainer, model.passwordError)
//        observe(binding.signupPasswordContainer, model.confirmPassword)
//        addChangeListener(binding.emailEditText, model::updateEmailError)
//        addChangeListener(binding.passwordEditText, model::updatePasswordError)
//        addChangeListener(binding.confirmPasswordEditText, model:: updateConfirmPassword)
    }

//    private fun observe(container: TextInputLayout, memberToObserve: MutableLiveData<String?>){
//        memberToObserve.observe(viewLifecycleOwner,  Observer<String?> { text ->
//            container.helperText = text
//        })
//    }
//    private fun addChangeListener(editText: TextInputEditText, modelViewFunction: KFunction1<String, Unit>){
//        editText.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                // do nothing
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                // do nothing
//            }
//
//            override fun afterTextChanged(newText: Editable?) {
//                modelViewFunction(newText.toString())
//            }
//        })
//    }
}
