package com.example.logintask1.util

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {
    @BindingAdapter("validateInput", "errorMessage")
    @JvmStatic
    fun setError(view: TextInputLayout, isValid: Boolean, error: String?){
       if(isValid){
           view.error = null
       }else{
           view.error = error
       }

    }


}

