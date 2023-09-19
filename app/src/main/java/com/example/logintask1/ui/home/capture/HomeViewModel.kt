package com.example.logintask1.ui.home.capture

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logintask1.data.ListItem

class HomeViewModel : ViewModel() {
    private val _personalPosts = MutableLiveData<List<ListItem>>()

    val personalPosts: LiveData<List<ListItem>> = _personalPosts

    fun addPersonalPost(post: ListItem){
        val currentPosts = _personalPosts.value?.toMutableList() ?: mutableListOf()
        currentPosts.add(post)
        _personalPosts.value = currentPosts
        Log.d("posts", _personalPosts.value.toString())
    }

    fun expandCard(item: ListItem){
        val updatedCard = item.copy(
            isExpanded = !item.isExpanded
        )

        val position = _personalPosts.value?.indexOfFirst { it.id == item.id } ?: 0
        val currentList = _personalPosts.value?.toMutableList() ?: mutableListOf()
        currentList[position] = updatedCard
        _personalPosts.value = currentList
    }

    fun getId(): Int{
        return (_personalPosts.value?.size?.plus(1)) ?: 1
    }
}