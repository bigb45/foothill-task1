package com.example.logintask1.ui.home.capture

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.logintask1.R
import com.example.logintask1.databinding.CustomAlertDialogBinding


class TitleDialogFragment : DialogFragment() {

    interface InputDialogListener {
        fun onInputConfirmed(title: String, details: String)
    }

    private var inputDialogListener: InputDialogListener? = null
    private lateinit var dialogBinding: CustomAlertDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogBinding = CustomAlertDialogBinding.inflate(layoutInflater)

        return AlertDialog.Builder(requireContext()).setMessage(getString(R.string.dialog_title))
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                confirmButtonListener()
            }.setView(dialogBinding.root).create()
    }

    companion object {
        const val TAG = "EnterTitleDialog"


    }

    fun showDialog(fragmentManager: FragmentManager) {
        show(fragmentManager, TAG)
    }

    fun setInputDialogListener(listener: InputDialogListener) {
        inputDialogListener = listener
    }

    private fun confirmButtonListener() {
        val title = dialogBinding.editTextTitle.text.toString()
        val details = dialogBinding.editTextDetails.text.toString()

        inputDialogListener?.onInputConfirmed(title, details)

    }


}