package com.example.logintask1.ui.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class SignupViewModel : ViewModel() {


    var email: MutableLiveData<String?> = MutableLiveData()
    var password: MutableLiveData<String?> = MutableLiveData()
    var confirmPassword: MutableLiveData<String?> = MutableLiveData()


    val emailError = email.map {

        validateEmail(it)

    }

    val passwordError = password.map {

        validatePassword(it)

    }

    val confirmPasswordError = confirmPassword.map {
        validateConfirmPassword(it)

    }

//    TODO: this function should return a boolean value to the binding adapter in the fragment, the
//     binding adapter takes that value and an error message and displays the error message on the
//     textInputLayout accordingly

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