package com.example.logintask1.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SigninViewModel : ViewModel() {

    //    private var _isEmailValid = MutableLiveData<Boolean>()
//    private val _isPasswordValid = MutableLiveData<Boolean>()
//
//
//    var isEmailValid: LiveData<Boolean> = _isEmailValid
//    var isPasswordValid: LiveData<Boolean> = _isPasswordValid
    private val _emailError = MutableLiveData<String?>()
    private val _passwordError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError
    val passwordError: LiveData<String?> = _passwordError
    var password: MutableLiveData<String?> = MutableLiveData()
    var email: MutableLiveData<String?> = MutableLiveData()


    // observe email and password changes, call validating functions when they change
    // another way is to use email.map and call the validation function inside
    init {
        email.observeForever { validateEmail() }
        password.observeForever { validatePassword() }
    }

    // function to validate email with regex
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

    // function to validate password

    fun validatePassword(): Boolean {
        val isValid = password.value.toString().length >= 8 || password.value?.isEmpty()?: true
        if(!isValid){
            _passwordError.value = "Password must be at least 8 characters"
        }else {
            _passwordError.value = null
        }
        return isValid

    }

}