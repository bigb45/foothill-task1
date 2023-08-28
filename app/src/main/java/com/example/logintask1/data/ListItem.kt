package com.example.logintask1.data

import android.net.Uri

data class ListItem(
    val id: Int,
    val title: String,
    val imageUri: Uri? = null,
    val details: String = "Nothing to show.",
    var isExpanded: Boolean = false
)