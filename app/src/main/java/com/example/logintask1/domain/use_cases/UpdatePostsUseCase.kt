package com.example.logintask1.domain.use_cases

import com.example.logintask1.domain.repository.PostsRepositoryImpl
import com.example.logintask1.ui.home.userpost.UserPost
import javax.inject.Inject

class UpdatePostsUseCase @Inject constructor(private val repository: PostsRepositoryImpl) {
    suspend operator fun invoke(post: UserPost) {
        repository.updatePost(post.postId, post)

    }
}