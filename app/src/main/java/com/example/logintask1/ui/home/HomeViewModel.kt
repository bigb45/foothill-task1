package com.example.logintask1.ui.home

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logintask1.data.ListItem
import java.util.Random

class HomeViewModel : ViewModel() {
    //     the variable that is changed in the model (or by the ui if using 2-way data-binding)
    private val _homeState: MutableLiveData<HomeUiModel> = MutableLiveData()
    private val _isExpanded: MutableLiveData<Boolean> = MutableLiveData(false)


    //    the variable that is exposed to the UI and passed to the binding adapter
    val homeState: LiveData<HomeUiModel> = _homeState
    val isExpanded: LiveData<Boolean> = _isExpanded

    fun addImage(imageUri: Uri, title: String, details: String?) {

        // Get the current list from the LiveData

        val currentList = homeState.value?.imagesList ?: emptyList()
        val id = Random().nextInt(200)
        val listItem = ListItem(id, title, imageUri, details)
        val newList = currentList + listItem
        _homeState.value = HomeUiModel(newList)

    }

    fun toggleItemsExpanded(){
        _isExpanded.value = !_isExpanded.value!!

    }
}