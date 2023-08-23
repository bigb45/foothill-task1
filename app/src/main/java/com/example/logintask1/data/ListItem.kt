package com.example.logintask1.data

import android.media.Image

data class ListItem(
    val id: Int,
    val title: String,
    val image: Image? = null,
    val details: String,
    var isExpanded: Boolean = false
)