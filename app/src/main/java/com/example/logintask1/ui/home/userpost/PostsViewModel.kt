package com.example.logintask1.ui.home.userpost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logintask1.data.api.Result
import com.example.logintask1.domain.use_cases.RequestsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val apiServices: RequestsUseCases
) :
    ViewModel() {
    private val _posts = MutableLiveData<List<UserPost>?>()
    private val _errorMessage = MutableLiveData<String?>()
    private val _isLoading = MutableLiveData(true)


    val errorMessage: LiveData<String?> = _errorMessage
    val isLoading: LiveData<Boolean?> = _isLoading
    val posts: MutableLiveData<List<UserPost>?> = _posts


// TODO add lazy loading to posts instead of loading all posts at once


    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            _errorMessage.postValue(null)
            when (val res = apiServices.getPostsUseCase()) {
                is Result.Success -> {
                    _posts.postValue(res.data)
                }
                is Result.Error -> {
                    _errorMessage.postValue("An error occurred while loading posts")
                }
            }
            _isLoading.postValue(false)
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