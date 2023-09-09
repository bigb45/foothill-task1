package com.example.logintask1.ui.home.userpost

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url
import java.util.Date

data class PostsUiModel(
    val postId: Int,
    val postTitle: String,
    val postBody: String,
    val userName: String,
    val formattedDate: String,
    val likes: Int,
//    val avatarUrl: Url?,
    val isLiked: Boolean
)