package com.example.logintask1.data.api

import com.example.logintask1.data.user.UserPost
import retrofit2.http.GET

interface UserPostApiService {
    @GET("posts")
    suspend fun getPosts(): List<UserPost>
}