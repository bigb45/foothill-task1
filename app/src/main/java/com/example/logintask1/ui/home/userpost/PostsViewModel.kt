package com.example.logintask1.ui.home.userpost

import android.util.Log
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logintask1.data.UserPost
import com.example.logintask1.network.UserPostApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<UserPost>>()
    val posts: LiveData<List<UserPost>> = _posts
    private val userPostsService: UserPostApiInterface = createPostsService()

    //TODO(move the create post service to a singleton object that should handle all service logic)

    private fun createPostsService(): UserPostApiInterface {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(UserPostApiInterface::class.java)
    }

    init {
        //TODO you need to handle the error
        viewModelScope.launch {
           makeGetPostsRequest()
        }.invokeOnCompletion {

        }
    }

    private suspend fun makeGetPostsRequest(){
        userPostsService.getPosts()
    }

    fun likePost(post:UserPost, position:Int){
        //TODO implement like post function
    }

    val likeClickListener = {
        post: UserPost, position: Int ->
        val updatedPost = post.copy(
            likes = if (post.isLiked) post.likes - 1 else post.likes + 1,
            isLiked = !post.isLiked
        )

        val currentList = _posts.value?.toMutableList() ?: mutableListOf()
        d("user", "old liked: ${currentList[position].isLiked}, id: ${currentList[position].postId}")
        d("user", "new liked: ${updatedPost.isLiked}, id: ${updatedPost.postId}")

        currentList[position] = updatedPost
        _posts.postValue(currentList)

    }


}