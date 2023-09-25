package com.example.logintask1.util

import androidx.annotation.StringRes
import com.example.logintask1.R
import javax.inject.Inject

val EMAIL_PATTERN = Regex(".+@.+(.com)$")
//  TODO: import context to use string resources instead of fixed string

class ValidationUtil @Inject constructor() {
    fun validateEmail(email: String): String?{

        if (!EMAIL_PATTERN.matches(email)) {
            return "Please enter a correct email"
        } else if(email.isEmpty()) {
            return "Email cannot be empty"
        }
        return null
    }

    fun validatePassword(password: String):PasswordValidation {

        if (password.length !in 8..12) {
            return PasswordValidation.EMPTY
        } else if(password.isEmpty()){
             return "Password cannot be empty"
        }
        return null
    }


    fun validateConfirmPassword(confirmPassword: String, password: String): Boolean{
        return (confirmPassword != password)
    }

}

enum class PasswordValidation (@StringRes val message:Int){
    EMPTY(R.string.password), NO_MATCH,
}