package com.example.logintask1.ui.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SigninViewModel: ViewModel() {


    var email: MutableLiveData<String?> = MutableLiveData()
    var emailError: MutableLiveData<String?> = MutableLiveData()
    var password: MutableLiveData<String?> = MutableLiveData()
    var passwordError: MutableLiveData<String?> = MutableLiveData()

    fun updateEmailError(newEmail: String){
        email.value = newEmail
        emailError.value = validateEmail()
    }

    fun updatePasswordError(newPassword: String){
        password.value = newPassword
        passwordError.value = validatePassword()
    }



    private fun validateEmail(): String?{

        val emailRegex = Regex(".+@.+(.com)$")
        if(!emailRegex.matches(email.value.toString())){
            return "Please enter a valid email"
        }
        return null
    }

    private fun validatePassword(): String?{
        if(password
                .value
                .toString()
                .length < 8){
            return "password must be at least 8 characters"
        }
        return null

    }

}