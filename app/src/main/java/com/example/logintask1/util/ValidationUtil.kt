package com.example.logintask1.util

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

    fun validatePassword(password: String): String? {

        if (password.length !in 8..12) {
            return "Password must be between 8 and 12 characters"
        } else if(password.isEmpty()){
             return "Password cannot be empty"
        }
        return null
    }


    fun validateConfirmPassword(confirmPassword: String, password: String): String? {
        return if (confirmPassword != password) {
            "Passwords don't match"
        } else {
            null
        }
    }

}