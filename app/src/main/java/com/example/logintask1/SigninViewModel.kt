package com.example.logintask1

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SigninViewModel: ViewModel() {


    var email: MutableLiveData<String> = MutableLiveData()
    var name: MutableLiveData<String> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData("test")
    var password: MutableLiveData<String> = MutableLiveData()

    fun updateError(newError: String){
        error.value = newError
    }
//    private fun emailChangeListener(){
//        val container = binding.emailContainer
//        binding.emailEditText.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                // do nothing
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                // do nothing
//            }
//
//            override fun afterTextChanged(newText: Editable?) {
//                container.helperText = validateEmail()
//            }
//        })
//
//    }
//
//    private fun passwordChangeListener(){
//        val container = binding.passwordContainer
//        binding.passwordEditText.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                // do nothing
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                // do nothing
//            }
//
//            override fun afterTextChanged(newText: Editable?) {
//                container.helperText = validatePassword()
//            }
//        })
//    }
//
//    private fun validateEmail(): String?{
//
//        val emailRegex = Regex(".+@.+[.com]$")
//        val email = binding.emailEditText.text.toString()
//
//        if(!emailRegex.matches(email)){
//            Log.d("match", "$email matches the pattern!")
//            return "incorrect email pattern"
//        }
//        return null
//    }
//
//    private fun validatePassword(): String?{
//        val password = binding.passwordEditText.text.toString()
//        if(password.length < 8){
//            return "password must be at least 8 characters"
//        }
//        return null
//
//    }

}