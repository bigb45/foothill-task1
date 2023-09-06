package com.example.logintask1.util

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
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

    @BindingAdapter("expand")
    @JvmStatic
    fun expandView(view: TextView, isExpanded: Boolean) {
        view.visibility = if(isExpanded) View.VISIBLE else View.GONE
    }

    @BindingAdapter("imageSource")
    @JvmStatic
    fun ImageView.setImageUrl(imageUri: Uri){
        Glide.with(context)
                .load(imageUri)
                .into(this)
    }

    @BindingAdapter("tvText")
    @JvmStatic
    fun TextView.setTvText(newText: String){
        this.text = newText
    }
}

