package com.example.logintask1.domain.use_cases

import com.example.logintask1.data.api.Result
import com.example.logintask1.domain.repository.PostsRepositoryImpl
import com.example.logintask1.ui.home.userpost.UserPost
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostsRepositoryImpl) {
    suspend operator fun invoke(): Result<List<UserPost>>{
        return try {
            val posts = repository.getPosts()
            return Result.Success(posts)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
