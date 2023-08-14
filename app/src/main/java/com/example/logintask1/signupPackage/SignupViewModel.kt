package com.example.logintask1.signupPackage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class SignupViewModel : ViewModel() {


    var email: MutableLiveData<String?> = MutableLiveData()
    var password: MutableLiveData<String?> = MutableLiveData()
    var confirmPassword: MutableLiveData<String?> = MutableLiveData()

    //    fun updateEmailError(newEmail: String){
//        email.value = newEmail
//        emailError.value = validateEmail()
//    }
//    fun updateConfirmPassword(newConfirmPassword: String){
//        confirmPassword.value = newConfirmPassword
//        updatePasswordError(password.value.toString())
//    }
//
//    fun updatePasswordError(newPassword: String){
//        password.value = newPassword
//        passwordError.value = validatePassword()
//
//    }
    val emailError = email.map {

            validateEmail(it)

    }

    val passwordError = password.map {

        validatePassword(it)

    }

    val confirmPasswordError = confirmPassword.map{
        validateConfirmPassword(it)

    }


    private fun validateEmail(email: String?): String? {
        val emailRegex = Regex(".+@.+(.com)$")
        if (!emailRegex.matches(email.toString())) {
            return "Please enter a valid email"
        }
        return null
    }

    private fun validatePassword(password: String?): String? {

        if (password.toString().length < 8) {
            return "password must have 8 or more characters"

        }
        return null
    }

    private fun validateConfirmPassword(confirmPassword: String?): String? {
        if (confirmPassword.toString() != password.value.toString()) {
            return "passwords don't match"
        }
        return null
    }
}