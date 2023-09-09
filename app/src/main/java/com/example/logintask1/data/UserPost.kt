package com.example.logintask1.data

import com.example.logintask1.ui.home.userpost.PostsUiModel
import com.google.gson.annotations.SerializedName
import retrofit2.http.Url
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class UserPost(
    val postId: Int,
    val postTitle: String,
    val postBody: String,
    @SerializedName("name")
    val userName: String,
    @SerializedName("createdAt")
    val createDate: Date,
    val likes: Int,
    val isLiked: Boolean
)

fun UserPost.toUiModel(): PostsUiModel {
    val formattedDate = createDate.getCustomPattern()
    return PostsUiModel(
        postId,
        postTitle,
        postBody,
        userName,
        formattedDate,
        likes,
        isLiked
    )
}

fun Date.getCustomPattern(): String {
    val dayOfMonth = SimpleDateFormat("d", Locale.getDefault()).format(this).toInt()
    val month = SimpleDateFormat("MMM", Locale.getDefault()).format(this)
    val year = SimpleDateFormat("yy", Locale.getDefault()).format(this)
    val hour = SimpleDateFormat("hh", Locale.getDefault()).format(this)
    val minutes = SimpleDateFormat("mm", Locale.getDefault()).format(this)
    val amPm = SimpleDateFormat("a", Locale.getDefault()).format(this)
    val suffix = getDayOfMonthSuffix(dayOfMonth)
    return "$dayOfMonth$suffix, $month '$year at $hour:$minutes $amPm"
}

fun getDayOfMonthSuffix(day: Int): String {
    if (day in (11..13))
        return "th"
    return when (day) {
        1 -> "st"
        2 -> "nd"
        3 -> "rd"
        else ->
            "th"
    }
}