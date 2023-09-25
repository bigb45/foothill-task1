package com.example.logintask1.domain.use_cases

import javax.inject.Inject

data class RequestsUseCases @Inject constructor(
    val getPostsUseCase: GetPostsUseCase,
    val updatePostsUseCase: UpdatePostsUseCase
)
