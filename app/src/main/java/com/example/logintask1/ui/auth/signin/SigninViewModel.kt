package com.example.logintask1.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logintask1.ui.auth.use_cases.ValidationUseCases
import com.example.logintask1.ui.auth.util.ValidationUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(private val validationUseCases: ValidationUseCases): ViewModel() {

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
        _emailError.value = validationUseCases.emailValidation.invoke(email.value.toString())
        return _emailError.value == null || email.value?.isEmpty() != false
    }


    fun validatePassword(): Boolean {
        _passwordError.value = validationUseCases.passwordValidation.invoke(password.value.toString())
        return _passwordError.value == null || password.value?.isEmpty() != false

    }

    fun validateFields(): Boolean {
        val emailCondition = validateEmail()
        val passwordCondition = validatePassword()
        return emailCondition && passwordCondition
    }


}