package com.example.logintask1.ui.auth.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {

    private val _emailError = MutableLiveData<String?>()
    private val _passwordError = MutableLiveData<String?>()
    private val _confirmPasswordError = MutableLiveData<String?>()

    val emailError: LiveData<String?> = _emailError
    val passwordError: LiveData<String?> = _passwordError
    val confirmPasswordError: LiveData<String?> = _confirmPasswordError

    var password: MutableLiveData<String?> = MutableLiveData()
    var email: MutableLiveData<String?> = MutableLiveData()
    var confirmPassword: MutableLiveData<String?> = MutableLiveData()

    init {
        email.observeForever { validateEmail() }
        password.observeForever { validatePassword() }
        confirmPassword.observeForever { validateConfirmPassword() }
    }

    fun validateEmail(): Boolean {
        val emailRegex = Regex(".+@.+(.com)$")
        val isValid = emailRegex.matches(email.value.toString()) || email.value?.isEmpty() ?: true
        if (!isValid) {
            Log.d("error", "incorrect email format")
            _emailError.value = "Incorrect email format"
        } else {
            Log.d("validation", emailRegex.matches(email.value.toString()).toString())
            _emailError.value = null
        }
        return isValid && email.value?.isEmpty() == false
    }

    fun validatePassword(): Boolean {
        val isValid =
            (password.value.toString().length in 8..12) || password.value?.isEmpty() ?: true
        if (!isValid) {
            _passwordError.value = "Password must be between 8 and 12 characters"
        } else {
            _passwordError.value = null
        }
        return isValid && password.value?.isEmpty() == false
    }

    fun validateConfirmPassword(): Boolean {
        return if (confirmPassword.value.toString() != password.value.toString()) {
            _confirmPasswordError.value = "Passwords don't match"
            false
        } else {
            _confirmPasswordError.value = null
            true
        }
    }

    fun validateFields(): Boolean {
        val emailCondition = validateEmail()
        val passwordCondition = validatePassword()
        val confirmPasswordCondition = validateConfirmPassword()
        return emailCondition && passwordCondition && confirmPasswordCondition
    }


}