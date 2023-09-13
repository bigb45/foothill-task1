package com.example.logintask1.ui.home.userpost

import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logintask1.data.UserPost
import com.example.logintask1.network.UserPostApiInterface
import com.example.logintask1.util.services.ApiServices
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<UserPost>>()
    val posts: LiveData<List<UserPost>> = _posts
    private val userPostsService: UserPostApiInterface = ApiServices.getPostsService()




    init {
        fetchPosts()
    }

    private fun fetchPosts(){
//        TODO: use this function to reload posts on button click
        viewModelScope.launch {
            try {
                _posts.postValue(makeGetPostsRequest())
            } catch (e: Exception) {
                e("error", e.toString())
                _posts.postValue(
                    listOf(
                        UserPost.errorPost
                    )
                )
            }
        }
    }
    private suspend fun makeGetPostsRequest(): List<UserPost> {
        return userPostsService.getPosts()
    }

    fun likePost(post: UserPost, position: Int) {
        val updatedPost = post.copy(
            likes = if (post.isLiked) post.likes - 1 else post.likes + 1,
            isLiked = !post.isLiked
        )

        val currentList = _posts.value?.toMutableList() ?: mutableListOf()
        currentList[position] = updatedPost
        _posts.postValue(currentList)
    }


}