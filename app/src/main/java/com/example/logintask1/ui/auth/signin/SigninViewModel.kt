package com.example.logintask1.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logintask1.domain.use_cases.EmailValidationUseCase
import com.example.logintask1.domain.use_cases.PasswordValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val emailValidator: EmailValidationUseCase,
    private val passwordValidator: PasswordValidationUseCase
) : ViewModel() {

    private val _emailError = MutableLiveData<Int?>()
    private val _passwordError = MutableLiveData<Int?>()
    val emailError: LiveData<Int?> = _emailError
    val passwordError: LiveData<Int?> = _passwordError
    var password: MutableLiveData<String?> = MutableLiveData()
    var email: MutableLiveData<String?> = MutableLiveData()

    init {
        email.observeForever { validateEmail() }
        password.observeForever { validatePassword() }
    }

    fun validateEmail(): Boolean {
        _emailError.value = emailValidator.invoke(email.value.toString())?.message
        return _emailError.value == null || email.value?.isEmpty() != false
    }


    fun validatePassword(): Boolean {
        _passwordError.value = passwordValidator.invoke(password.value.toString())?.message
        return _passwordError.value == null || password.value?.isEmpty() != false
    }

    fun validateFields(): Boolean {
        val emailCondition = emailValidator.invoke(email.value.toString()) == null
        val passwordCondition = passwordValidator.invoke(password.value.toString()) == null
        return emailCondition && passwordCondition
    }
}