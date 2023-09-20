package com.example.logintask1.ui.auth.use_cases

import com.example.logintask1.ui.auth.util.ValidationUtil
import javax.inject.Inject

class PasswordValidationUseCase @Inject constructor(private val validationUtil: ValidationUtil){
    operator fun invoke(password: String): String?{
        return validationUtil.validatePassword(password)

    }
}