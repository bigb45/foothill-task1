package com.example.logintask1.util

import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {
    @BindingAdapter("validateText", "errorMessage")
    @JvmStatic
    fun setError(view: TextInputLayout, isValid: Boolean, error: String?){
       if(isValid){
           view.error = null
           // do some other actions e.g prevent signing in
       }else{
           view.error = error
           // do other actions...
       }

    }
}