package com.example.logintask1.signinPackage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class SigninViewModel: ViewModel() {


    var email: MutableLiveData<String?> = MutableLiveData()
    var password: MutableLiveData<String?> = MutableLiveData()


    val emailError = email.map {
        validateEmail(it)
    }

    val passwordError = password.map {
        validatePassword(it)
    }





    private fun validateEmail(email: String?): String?{
        val emailRegex = Regex(".+@.+(.com)$")
        if(!emailRegex.matches(email.toString())){
            return "Please enter a valid email"
        }
        return null
    }

    private fun validatePassword(password: String?): String?{
        if(password.toString().length < 8){
            return "password must be at least 8 characters"
        }
        return null

    }

}