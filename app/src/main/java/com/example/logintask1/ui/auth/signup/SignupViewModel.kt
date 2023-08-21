package com.example.logintask1.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {

    private val _emailError = MutableLiveData<String?>()
    private val _passwordError = MutableLiveData<String?>()
    private val _confirmPasswordError = MutableLiveData<String?>()

    // data to be set in the view
    val emailError: LiveData<String?> = _emailError
    val passwordError: LiveData<String?> = _passwordError
    val confirmPasswordError: LiveData<String?> = _confirmPasswordError

    // data coming from view
    var password: MutableLiveData<String?> = MutableLiveData()
    var email: MutableLiveData<String?> = MutableLiveData()
    var confirmPassword: MutableLiveData<String?> = MutableLiveData()

    init {
        email.observeForever { validateEmail() }
        password.observeForever { validatePassword() }
        confirmPassword.observeForever{ validateConfirmPassword() }
    }

    fun validateEmail(): Boolean {
        val emailRegex = Regex(".+@.+(.com)$")
        val isValid = emailRegex.matches(email.value.toString()) || email.value?.isEmpty() ?: true
        if (!isValid) {
            _emailError.value = "Incorrect email format"
        } else {
            _emailError.value = null
        }
        return isValid
    }
    fun validatePassword(): Boolean {
        val isValid = password.value.toString().length >= 8 || password.value?.isEmpty()?: true
        if(!isValid){
            _passwordError.value = "Password must be at least 8 characters"
        }else {
            _passwordError.value = null
        }
        return isValid
    }

    fun validateConfirmPassword(): Boolean {
        return if (confirmPassword.value.toString() != password.value.toString()) {
            _confirmPasswordError.value = "Passwords don't match"
            false
        }else{
            _confirmPasswordError.value = null
            true
        }
    }
}