package com.example.logintask1.ui.home.userpost

import android.net.Uri
import com.example.logintask1.R
import com.example.logintask1.data.user.PostButtonStateIcons

data class PostsUiModel(
    val postId: Int,
    val postTitle: String,
    val postBody: String,
    val userName: String,
    val formattedDate: String,
    val likesInt: Int,
    val isLiked: Boolean,
    val avatarUrl: Uri,
    val isSaved: Boolean
){
    val likes = likesInt.toString()
    val saveButtonStateIcons = PostButtonStateIcons(
        R.drawable.outline_bookmark_border_24,
        R.drawable.baseline_bookmark_24
    )

    val likeButtonStateIcons = PostButtonStateIcons(
        R.drawable.round_favorite_border_24,
        R.drawable.round_favorite_24
    )
}
