package com.example.logintask1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Log.ASSERT
import android.util.Log.INFO
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.logintask1.databinding.FragmentSigninBinding
import java.util.logging.Level.INFO


class SigninFragment : Fragment(R.layout.fragment_signin) {
    private lateinit var binding: FragmentSigninBinding
    private val model: SigninViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSigninBinding.bind(view)
        binding.viewModel = model

        val ob = Observer<String> { text ->
            Log.d("textbox changed", text)
            binding.emailContainer.helperText = text

        }

        model.error.observe(viewLifecycleOwner, ob)
        changeListener()
    }

    private fun changeListener(){
        binding.emailEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun afterTextChanged(newText: Editable?) {
                model.updateError(newText.toString())
            }
        })
    }

}

