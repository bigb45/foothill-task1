package com.example.logintask1.util

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.logintask1.data.ListItem
import com.example.logintask1.ui.home.adapters.UsersListAdapter
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

    @BindingAdapter("items")
    @JvmStatic
    fun setAdapterList(view: RecyclerView, items: List<ListItem>?){
        Log.d("items", items.toString())
        val adapter = view.adapter as UsersListAdapter
        adapter.submitList(items)
    }

    @BindingAdapter("expand")
    @JvmStatic
    fun expandView(view: TextView, isExpanded: Boolean) {
        Log.d("view is visible:", isExpanded.toString())
        if(isExpanded) {
            Log.d("view", "is visible")

            view.visibility = View.VISIBLE
        } else {
            Log.d("view", "is not visible")

            view.visibility = View.GONE
        }

    }




}

