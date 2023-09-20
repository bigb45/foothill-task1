package com.example.logintask1.ui.auth.signup

data class ValidationResult(
    val emailError: String?,
    val passwordError: String?,
    val confirmPassword: String?,
    val isValid: Boolean,
)
