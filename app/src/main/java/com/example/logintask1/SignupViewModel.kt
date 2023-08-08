package com.example.logintask1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.PasswordAuthentication

class SignupViewModel: ViewModel() {


    private var email: MutableLiveData<String?> = MutableLiveData()
    var emailError: MutableLiveData<String?> = MutableLiveData()
    private var password: MutableLiveData<String?> = MutableLiveData()
    var passwordError: MutableLiveData<String?> = MutableLiveData()
    var confirmPassword: MutableLiveData<String?> = MutableLiveData()

    fun updateEmailError(newEmail: String){
        email.value = newEmail
        emailError.value = validateEmail()
    }
    fun updateConfirmPassword(newConfirmPassword: String){
        confirmPassword.value = newConfirmPassword
        updatePasswordError(password.value.toString())
    }

    fun updatePasswordError(newPassword: String){
        password.value = newPassword
        passwordError.value = validatePassword()

    }


    private fun validateEmail(): String?{
        val emailRegex = Regex(".+@.+[.com]$")
        if(!emailRegex.matches(email.toString())){
            return "Please enter a valid email"
        }
        return null
    }

    private fun validatePassword(): String?{
        if(password
                .value
                .toString()
                .length < 8){
            return "password must have 8 or more characters"
        }

        if(confirmPassword.value.toString() != password.value.toString()){
            return "passwords don't match"
        }
        return null

    }
}