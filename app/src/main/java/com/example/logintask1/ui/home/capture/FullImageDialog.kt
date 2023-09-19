package com.example.logintask1.ui.home.capture

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.logintask1.R
import com.example.logintask1.databinding.FragmentFullImageDialogBinding
import com.example.logintask1.util.BindingAdapters.setImageSource


class FullImageDialog : DialogFragment() {
    private lateinit var binding: FragmentFullImageDialogBinding

    companion object {
        private const val IMAGE_URI_KEY = "imageUri"

        fun newInstance(imageUri: Uri): FullImageDialog {

            val args = Bundle()
            args.putString(IMAGE_URI_KEY, imageUri.toString())
            val fragment = FullImageDialog()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_full_image_dialog, container, false)
        val imageUri = arguments?.getString(IMAGE_URI_KEY)?.toUri() ?: "".toUri()
        binding.imageView.setImageSource(imageUri)
        return binding.root

    }

}