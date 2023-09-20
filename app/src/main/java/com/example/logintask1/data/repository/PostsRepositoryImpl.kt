    package com.example.logintask1.data.repository

    import com.example.logintask1.data.api.UserPostApiService
    import com.example.logintask1.data.user.UserPost
    import dagger.Provides
    import javax.inject.Inject


    class PostsRepositoryImpl @Inject constructor(private val apiService: UserPostApiService): PostsRepository{
        override suspend fun getPosts(): List<UserPost>{
            return apiService.getPosts()
        }
    }