package com.example.logintask1.signinPackage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.logintask1.R
import com.example.logintask1.databinding.FragmentSigninBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.reflect.KFunction1


class SigninFragment : Fragment(R.layout.fragment_signin) {
    private lateinit var binding: FragmentSigninBinding
    private val model: SigninViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSigninBinding.bind(view)
        binding.viewModel = model
        binding.lifecycleOwner = this



//        observe(binding.emailContainer, model.emailError)
//        observe(binding.passwordContainer, model.passwordError)
//        addChangeListener(binding.emailEditText, model::updateEmailError)
//        addChangeListener(binding.passwordEditText, model::updatePasswordError)

    }

    // TODO: remove this
//    private fun observe(container: TextInputLayout, memberToObserve: MutableLiveData<String?>){
//        memberToObserve.observe(viewLifecycleOwner,  Observer<String?> { text ->
//            container.helperText = text
//        })
//    }
    private fun addChangeListener(container: TextInputEditText, modelViewFunction: KFunction1<String, Unit>){
        container.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun afterTextChanged(newText: Editable?) {
                modelViewFunction(newText.toString())
            }
        })
    }

}

