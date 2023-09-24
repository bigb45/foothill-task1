package com.example.logintask1.domain.use_cases

import javax.inject.Inject

data class ValidationUseCases @Inject constructor(
    val confirmPasswordValidation: ConfirmPasswordValidationUseCase,
    val passwordValidation: PasswordValidationUseCase,
    val emailValidation: EmailValidationUseCase,

    )
