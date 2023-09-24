package com.example.logintask1.domain.use_cases

import com.example.logintask1.util.ValidationUtil
import javax.inject.Inject

class ConfirmPasswordValidationUseCase @Inject constructor(private val validationUtil: ValidationUtil){
    operator fun invoke(confirmPassword: String, password: String): String?{
        return validationUtil.validateConfirmPassword(confirmPassword, password)

    }
}