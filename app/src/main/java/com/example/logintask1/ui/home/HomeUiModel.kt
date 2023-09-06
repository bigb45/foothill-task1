package com.example.logintask1.ui.home

import android.net.Uri
import android.util.Log
import com.example.logintask1.data.ListItem

data class HomeUiModel(
    val title: String,
    val imageUri: Uri? = null,
    val details: String? = "Nothing to show.",
    var isExpanded: Boolean = false

)
