package com.example.logintask1.signinPackage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class SigninViewModel: ViewModel() {


    var email: MutableLiveData<String?> = MutableLiveData()
//    var emailError: MutableLiveData<String?> = MutableLiveData()
    var password: MutableLiveData<String?> = MutableLiveData()
//    var passwordError: MutableLiveData<String?> = MutableLiveData()
    var test: MutableLiveData<String?> = MutableLiveData("hi mom this is a test")


    val emailError = email.map {
        validateEmail(it)
    }

    val passwordError = password.map {
        validatePassword(it)
    }

    // TODO: remove this
//    fun updateEmailError(newEmail: String){
//        email.value = newEmail
//        emailError.value = validateEmail()
//        test.value = newEmail
//    }
//
//    fun updatePasswordError(newPassword: String){
//        password.value = newPassword
//        passwordError.value = validatePassword()
//    }



    private fun validateEmail(email: String?): String{
        val emailRegex = Regex(".+@.+(.com)$")
        if(!emailRegex.matches(email.toString())){
            return "Please enter a valid email"
        }
        return ""
    }

    private fun validatePassword(password: String?): String?{
        if(password.toString().length < 8){
            return "password must be at least 8 characters"
        }
        return null

    }

}