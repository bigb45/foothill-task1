package com.example.logintask1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
<<<<<<< Updated upstream
import android.text.style.UnderlineSpan
import android.util.Log
=======
import android.util.Log
import android.view.View
>>>>>>> Stashed changes
import androidx.fragment.app.Fragment
import com.example.logintask1.databinding.FragmentSigninBinding

class SigninFragment : Fragment(R.layout.fragment_signin) {
    private lateinit var binding: FragmentSigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSigninBinding.inflate(layoutInflater)

        val createAccountText = binding.signinText
        val signinButton = binding.signinButton


        val text = createAccountText.text
        val spannable = SpannableString(text)
        spannable.setSpan(UnderlineSpan(), 0, text.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        createAccountText.text = spannable
        emailChangeListener()
        passwordChangeListener()

        signinButton.setOnClickListener {
            val isPasswordValid = (binding.emailContainer.helperText == null)
            val isEmailValid = (binding.passwordContainer.helperText == null)
            if(isEmailValid && isPasswordValid){
                Log.d("hi", "hi")
            }
        }
        createAccountText.setOnClickListener {
            Log.d("Debug", "creating new account")

        }

    }

<<<<<<< Updated upstream
    private fun emailChangeListener(){
        val container = binding.emailContainer
        binding.emailEditText.addTextChangedListener(object: TextWatcher {
=======
    private fun observe(container: TextInputLayout, memberToObserve: MutableLiveData<String?>){
        memberToObserve.observe(viewLifecycleOwner,  Observer<String?> { text ->
            container.helperText = text
        })
    }
    private fun addChangeListener(editText: TextInputEditText, modelViewFunction: KFunction1<String, Unit>){
        editText.addTextChangedListener(object: TextWatcher {
>>>>>>> Stashed changes
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun afterTextChanged(newText: Editable?) {
<<<<<<< Updated upstream
                container.helperText = validateEmail()
=======
                modelViewFunction(newText.toString())
                Log.d("test", newText.toString())
>>>>>>> Stashed changes
            }
        })

    }

    private fun passwordChangeListener(){
        val container = binding.passwordContainer
        binding.passwordEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun afterTextChanged(newText: Editable?) {
                container.helperText = validatePassword()
            }
        })


    }

    private fun validateEmail(): String?{

        val emailRegex = Regex(".+@.+[.com]$")
        val email = binding.emailEditText.text.toString()

        if(!emailRegex.matches(email)){
            Log.d("match", "$email matches the pattern!")
            return "incorrect email pattern"
        }
        return null
    }

    private fun validatePassword(): String?{
        val password = binding.passwordEditText.text.toString()
        if(password.length < 8){
            return "password must be at least 8 characters"
        }
        return null

    }
}

