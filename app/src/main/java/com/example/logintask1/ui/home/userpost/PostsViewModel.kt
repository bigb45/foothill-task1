package com.example.logintask1.ui.home.userpost

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logintask1.data.api.Result
import com.example.logintask1.domain.use_cases.GetPostsUseCase
import com.example.logintask1.domain.use_cases.RequestsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val apiServices: RequestsUseCases,
    private val getPostsUseCase: GetPostsUseCase
) :
    ViewModel() {
    private val _posts = MutableLiveData<List<UserPost>?>()
    private val _errorMessage = MutableLiveData<String?>()
    private val _isLoading = MutableLiveData(true)
    private val _snackbarError = MutableLiveData<String?>()


    val errorMessage: LiveData<String?> = _errorMessage
    val isLoading: LiveData<Boolean?> = _isLoading
    val posts: MutableLiveData<List<UserPost>?> = _posts
    val snackbarError: LiveData<String?> = _snackbarError

// TODO add lazy loading to posts instead of loading all posts at once


    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch(errorHandler()) {
            val posts = getPostsUseCase()
            _posts.postValue(posts)
            _snackbarError.postValue(null)
        }
    }

    private fun errorHandler() = CoroutineExceptionHandler { _, throwable ->
        when(throwable){
            is IOException -> {
                _snackbarError.postValue("Error while fetching data")
            }
            is NetworkErrorException -> {
                _snackbarError.postValue("A network error occurred")

            }
            else -> {
                _snackbarError.postValue("Unhandled error")

            }
        }
    }

    fun savePost(post: UserPost) {
        val updatedPost = post.copy(
            isSaved = !post.isSaved
        )
        val position = _posts.value?.indexOfFirst { it.postId == post.postId } ?: 0
        val currentList = _posts.value?.toMutableList() ?: mutableListOf()
        currentList[position] = updatedPost
        _posts.postValue(currentList)
        updatePost(updatedPost)
    }

    fun likePost(post: UserPost) {
        val updatedPost = post.copy(
            likes = if (post.isLiked) post.likes - 1 else post.likes + 1, isLiked = !post.isLiked
        )
        val position = _posts.value?.indexOfFirst { it.postId == post.postId } ?: 0
        val currentList = _posts.value?.toMutableList() ?: mutableListOf()
        currentList[position] = updatedPost
        _posts.postValue(currentList)
        updatePost(updatedPost)
    }

    private fun updatePost(post: UserPost) {
        viewModelScope.launch {
            apiServices.updatePostsUseCase(post)
        }
    }
}