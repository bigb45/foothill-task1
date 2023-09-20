package com.example.logintask1.data.api

import com.example.logintask1.data.user.UserPost
import retrofit2.http.GET

/*
* This is the contract for the interacting with the posts API
* */
interface UserPostApiService {
    /*
    * Retrieves a list of posts from the remote API
    * returns a list of UserPost
    * */
    @GET("posts")
    suspend fun getPosts(): List<UserPost>
}