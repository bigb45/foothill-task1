package com.example.logintask1.ui.auth.use_cases

import com.example.logintask1.ui.auth.util.ValidationUtil
import javax.inject.Inject

class EmailValidationUseCase @Inject constructor(private val validationUtil: ValidationUtil){
    operator fun invoke(email: String): String?{
        return validationUtil.validateEmail(email)

    }
}