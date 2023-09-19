package com.example.logintask1.network

import com.example.logintask1.data.UserPost
import retrofit2.http.GET

interface UserPostApiInterface {

    @GET("posts")
    suspend fun getPosts(): List<UserPost>
}