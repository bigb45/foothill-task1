package com.example.logintask1.data.repository

import com.example.logintask1.data.user.UserPost

/*
* The repository interface used to handle data and remote requests
* */
interface PostsRepository {
    /*
    * calls the function which fetches data from the remote API (retrofit API call) returns a list
    * of UserPost
    */
    suspend fun getPosts(): List<UserPost>

    // TODO: implement putPost to be used to like and save posts
    suspend fun putPost(): UserPost
}