package com.example.logintask1.ui.home.capture

import android.graphics.Bitmap
import android.net.Uri

data class ListItem(
    val id: Int,
    val title: String,
    val imageUri: Uri? = null,
    val details: String? = "Nothing to show.",
    val thumbnail: Bitmap,
    var isExpanded: Boolean = false,
)

fun ListItem.toUiModel() : HomeUiModel {
    return HomeUiModel(title, imageUri, details, thumbnail, isExpanded)
}