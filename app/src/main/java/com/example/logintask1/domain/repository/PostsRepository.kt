package com.example.logintask1.domain.repository

import com.example.logintask1.domain.user.UserPost

/*
* The repository interface used to handle data and remote requests
* */
interface PostsRepository {
    /*
    * calls the function which fetches data from the remote API (retrofit API call) returns a list
    * of UserPost
    */
    suspend fun getPosts(): List<UserPost>

    /*
    * makes a put request to the api when the user likes or saves a post
    */
    suspend fun updatePost(id: Int, post: UserPost): UserPost
}