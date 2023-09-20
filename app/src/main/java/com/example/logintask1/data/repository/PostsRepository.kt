package com.example.logintask1.data.repository

import com.example.logintask1.data.user.UserPost

interface PostsRepository {
    suspend fun getPosts(): List<UserPost>
}