package com.example.logintask1.data

import android.net.Uri

data class ListItem(
//   remove the id
    val id: Int,
    val title: String,
    val imageUri: Uri? = null,
//    mentor suggested I do something with this but I forget.
    val details: String? = "Nothing to show.",
//    replace with binding adapter
    var isExpanded: Boolean = false
)