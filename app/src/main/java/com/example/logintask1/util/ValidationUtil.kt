package com.example.logintask1.util

import androidx.annotation.StringRes
import com.example.logintask1.R
import javax.inject.Inject

val EMAIL_PATTERN = Regex(".+@.+(.com)$")

class ValidationUtil @Inject constructor() {
    fun validateEmail(email: String): ValidationResult? {

        if (!EMAIL_PATTERN.matches(email)) {
            return ValidationResult.WRONG_PATTERN
        } else if(email.isEmpty()) {
            return ValidationResult.EMPTY_EMAIL
        }
        return null
    }

    fun validatePassword(password: String): ValidationResult? {

        if (password.length !in 8..12) {
            return ValidationResult.PASSWORD_LENGTH
        } else if(password.isEmpty()){
             return ValidationResult.EMPTY_PASSWORD
        }
        return null
    }


    fun validateConfirmPassword(confirmPassword: String, password: String): ValidationResult?{
        return if (confirmPassword != password) ValidationResult.NO_MATCH else null

    }

}

enum class ValidationResult (@StringRes val message: Int){
    PASSWORD_LENGTH(R.string.password_length_error),
    EMPTY_PASSWORD(R.string.password),
    NO_MATCH(R.string.no_match),
    WRONG_PATTERN(R.string.wrong_email_pattern_error),
    EMPTY_EMAIL(R.string.empty_email_error)
}