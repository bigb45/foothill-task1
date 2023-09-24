package com.example.logintask1.domain.use_cases

import com.example.logintask1.util.ValidationUtil
import javax.inject.Inject

class EmailValidationUseCase @Inject constructor(private val validationUtil: ValidationUtil){
    operator fun invoke(email: String): String?{
        return validationUtil.validateEmail(email)

    }
}