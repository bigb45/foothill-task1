package com.example.logintask1.data.api

import com.example.logintask1.ui.home.userpost.UserPost
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import javax.inject.Inject

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

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body updatedPost: UserPost): UserPost

    @POST("posts")
    suspend fun uploadPost(@Body post: UserPost)
}