package com.example.logintask1.ui.home.userpost

import android.util.Log
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
import java.lang.Exception

class PostsViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<UserPost>>()
    val posts: LiveData<List<UserPost>> = _posts
    private val userPostsService: UserPostApiInterface = createPostsService()

    private fun createPostsService(): UserPostApiInterface {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(UserPostApiInterface::class.java)
    }

    init{
        getPostsRequest()
    }
    private fun getPostsRequest(){
       viewModelScope.launch {
           try{
               val response = withContext(Dispatchers.IO){
                   userPostsService.getPosts().execute()
               }
               if(response.isSuccessful){
                   _posts.value = response.body()
               }else{
                   Log.e("error", "Error while fetching data from remote API.")

               }
           }catch(e: Exception) {
               Log.e("error", e.message.toString())
           }
       }

    }
}