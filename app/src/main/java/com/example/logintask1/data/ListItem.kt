package com.example.logintask1.data

import android.net.Uri
import com.example.logintask1.ui.home.HomeUiModel

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

fun ListItem.toUiModel() : HomeUiModel{
    return HomeUiModel(title, imageUri, details, isExpanded)
}