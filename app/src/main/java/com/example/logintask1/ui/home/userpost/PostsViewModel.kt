package com.example.logintask1.ui.home.userpost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logintask1.domain.api.Result
import com.example.logintask1.domain.user.UserPost
import com.example.logintask1.domain.repository.PostsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostsRepositoryImpl) : ViewModel() {
    private val _posts = MutableLiveData<List<UserPost>>()
    private val _errorMessage = MutableLiveData<String?>()
    private val _isLoading = MutableLiveData(true)


    val errorMessage: LiveData<String?> = _errorMessage
    val isLoading: LiveData<Boolean?> = _isLoading
    val posts: LiveData<List<UserPost>> = _posts



// TODO add lazy loading to posts instead of loading all posts at once


    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            _errorMessage.postValue(null)
            when(val res = makeGetPostsRequest()) {
                is Result.Success -> {
                    _posts.postValue(res.data?: emptyList())
                }

                is Result.Error -> {
                    _errorMessage.postValue("An error occurred while loading posts")

                }
            }
            _isLoading.postValue(false)
        }
    }

    private suspend fun makeGetPostsRequest(): Result<List<UserPost>> {
        return try{
            val posts = repository.getPosts()
            return Result.Success(posts)
        }catch (e: Exception){
            Result.Error(e)
        }
    }

    fun savePost(post: UserPost){
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
            likes = if (post.isLiked) post.likes - 1 else post.likes + 1,
            isLiked = !post.isLiked
        )
        val position = _posts.value?.indexOfFirst { it.postId == post.postId } ?: 0
        val currentList = _posts.value?.toMutableList() ?: mutableListOf()
        currentList[position] = updatedPost
        _posts.postValue(currentList)
        updatePost(updatedPost)
    }

    private fun updatePost(post: UserPost){
        viewModelScope.launch {
            repository.updatePost(post.postId, post)
        }
    }


}