package com.example.logintask1.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SigninViewModel : ViewModel() {

    private val _emailError = MutableLiveData<String?>()
    private val _passwordError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError
    val passwordError: LiveData<String?> = _passwordError
    var password: MutableLiveData<String?> = MutableLiveData()
    var email: MutableLiveData<String?> = MutableLiveData()

    init {
        email.observeForever { validateEmail() }
        password.observeForever { validatePassword() }
    }

    fun validateEmail(): Boolean {
        val emailRegex = Regex(".+@.+(.com)$")
        val isValid = emailRegex.matches(email.value.toString()) || email.value?.isEmpty() ?: true
        if (!isValid) {
            _emailError.value = "Incorrect email format"
        } else {
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

    fun validateFields(): Boolean {
        val emailCondition = validateEmail()
        val passwordCondition = validatePassword()
        return emailCondition && passwordCondition
    }


}