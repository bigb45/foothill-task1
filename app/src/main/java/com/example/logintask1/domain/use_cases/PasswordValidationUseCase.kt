package com.example.logintask1.domain.use_cases

import com.example.logintask1.util.ValidationResult
import com.example.logintask1.util.ValidationUtil
import javax.inject.Inject

class PasswordValidationUseCase @Inject constructor(private val validationUtil: ValidationUtil){
    operator fun invoke(password: String): ValidationResult?{
        return validationUtil.validatePassword(password)

    }
}