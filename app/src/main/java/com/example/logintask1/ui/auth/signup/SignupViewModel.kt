package com.example.logintask1.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logintask1.ui.auth.use_cases.ValidationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val validateUseCases: ValidationUseCases) :
    ViewModel() {

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
        _emailError.value = validateUseCases.emailValidation.invoke(email.value.toString())
        return _emailError.value == null || email.value?.isEmpty() != false
    }

    fun validatePassword(): Boolean {
        _passwordError.value = validateUseCases.passwordValidation.invoke(password.value.toString())
        return _passwordError.value == null || password.value?.isEmpty() != false
    }

    fun validateConfirmPassword(): Boolean {
        _confirmPasswordError.value = validateUseCases.confirmPasswordValidation(confirmPassword.value.toString(), password.value.toString())

        return _confirmPasswordError.value  == null
    }

    fun validateFields(): Boolean {
        with(validateUseCases){
            val emailCondition = emailValidation.invoke(email.value.toString()).isNullOrEmpty()
            val passwordCondition = passwordValidation.invoke(password.value.toString()).isNullOrEmpty()
            val confirmPasswordCondition = confirmPasswordValidation.invoke(confirmPassword.value.toString(), password.value.toString()).isNullOrEmpty()
            return emailCondition && passwordCondition && confirmPasswordCondition
        }
    }


}