package com.example.logintask1.domain.repository

import com.example.logintask1.domain.api.UserPostApiService
import com.example.logintask1.domain.user.UserPost
import javax.inject.Inject

/*
* concrete implementation of the PostsRepository interface, this class is injected into the
* PostsViewModel and used to make the needed API requests, we inject the UserPostApiService into
* the constructor to be able to make the API requests
*/
class PostsRepositoryImpl @Inject constructor(private val apiService: UserPostApiService) :
    PostsRepository {
    /*
    * this function is used to call the API request from the viewModel
    * no need to use @Provides since we use constructor injection in the viewModel
    */
    override suspend fun getPosts(): List<UserPost> {
        return apiService.getPosts()
    }

    override suspend fun putPost(): UserPost {
        TODO("Not yet implemented")
    }
}