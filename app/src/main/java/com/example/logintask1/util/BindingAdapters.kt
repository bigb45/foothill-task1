package com.example.logintask1.util

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.logintask1.domain.user.PostButtonStateIcons
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {
    @BindingAdapter("validateInput", "errorMessage")
    @JvmStatic
    fun setError(view: TextInputLayout, isValid: Boolean, error: String?) {
        if (isValid) {
            view.error = null
        } else {
            view.error = error
        }
    }

    @BindingAdapter("expand")
    @JvmStatic
    fun expandView(view: TextView, isExpanded: Boolean) {
        view.visibility = if (isExpanded) View.VISIBLE else View.GONE
    }

    @BindingAdapter("imageSource")
    @JvmStatic
    fun ImageView.setImageSource(imageSource: Uri) {
        imageSource.let {
            Glide.with(context).load(imageSource).into(this)
        }
    }

    @BindingAdapter(value = ["buttonIcons", "isFilled"])
    @JvmStatic
    fun Button.setButtonIcons(buttonIcons: PostButtonStateIcons, isFilled: Boolean) {
        val icon =
            if (isFilled) buttonIcons.defaultDrawableResId else buttonIcons.filledDrawableResId
        this.setBackgroundResource(icon)
    }

    @BindingAdapter("thumbnail")
    @JvmStatic
    fun ImageView.setImageThumbnail(imageBitmap: Bitmap) {
        this.setImageBitmap(imageBitmap)
    }

    @BindingAdapter("tvText")
    @JvmStatic
    fun TextView.setTvText(newText: String) {
        this.text = newText
    }

    @BindingAdapter("setLoadingErrorText")
    @JvmStatic
    fun TextView.setLoadingError(errorText: String?) {
        Log.d("binding error", errorText ?: "empty")

        errorText?.let {
            this.text = errorText
            this.visibility = View.VISIBLE
        }
        if (errorText.isNullOrEmpty()) this.visibility = View.GONE

    }

    @BindingAdapter("setIsLoading")
    @JvmStatic
    fun ProgressBar.setVisible(isVisible: Boolean) {
        this.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}

