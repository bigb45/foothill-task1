package com.example.logintask1.ui.home.capture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logintask1.domain.repository.PostsRepositoryImpl
import com.example.logintask1.ui.home.userpost.UserPost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PostsRepositoryImpl): ViewModel() {
    private val _personalPosts = MutableLiveData<List<ListItem>>()

    val personalPosts: LiveData<List<ListItem>> = _personalPosts
    val hasItems: MutableLiveData<Boolean> = MutableLiveData(false)
    fun addPersonalPost(post: ListItem){
        val currentPosts = _personalPosts.value?.toMutableList() ?: mutableListOf()
        currentPosts.add(post)
        _personalPosts.value = currentPosts
        hasItems.value = _personalPosts.value?.size != 0

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
    fun uploadPost(post: UserPost){
        viewModelScope.launch {
            repository.uploadPost(post)
        }

    }
}