package com.example.logintask1.util.services

import com.example.logintask1.network.UserPostApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val POSTS_BASE_URL = "https://64fce528605a026163aedf15.mockapi.io/"

object ApiServices {

        private val userPostsApiService: UserPostApiInterface by lazy {
            Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(POSTS_BASE_URL)
                .build()
                .create(UserPostApiInterface::class.java)
        }
        fun getPostsService(): UserPostApiInterface{
            return userPostsApiService;
        }
// add other services here such as login api, etc.

}