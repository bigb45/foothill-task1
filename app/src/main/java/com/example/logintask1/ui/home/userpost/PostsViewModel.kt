package com.example.logintask1.ui.home.userpost

import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logintask1.data.UserPost
import com.example.logintask1.network.UserPostApiInterface
import com.example.logintask1.di.ApiServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(apiService: UserPostApiInterface) : ViewModel() {
    private val _posts = MutableLiveData<List<UserPost>>()
    private val _errorMessage = MutableLiveData<String?>()
    private val _isLoading = MutableLiveData(true)



    val errorMessage: LiveData<String?> = _errorMessage
    val isLoading: LiveData<Boolean?> = _isLoading
    val posts: LiveData<List<UserPost>> = _posts

    private val userPostsService: UserPostApiInterface = apiService

// TODO add lazy loading to posts instead of loading all posts at once


    init {
        fetchPosts()
    }

    fun fetchPosts(){
        viewModelScope.launch {
            try {
                _isLoading.postValue(true)
                _errorMessage.postValue(null)

                _posts.postValue(makeGetPostsRequest())
            } catch (e: Exception) {
                e("error", e.toString())
//                _posts.postValue(
//                    listOf(
//                        UserPost.errorPost
//                    )
//                )
                _errorMessage.postValue("An error occurred while loading posts")
            } finally {
                _isLoading.postValue(false)
            }

        }



    }
    private suspend fun makeGetPostsRequest(): List<UserPost> {
        return userPostsService.getPosts()
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
    }


}