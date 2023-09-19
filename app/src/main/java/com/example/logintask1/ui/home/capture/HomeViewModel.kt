package com.example.logintask1.ui.home.capture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    //     the variable that is changed in the model (or by the ui if using 2-way data-binding)
    private val _homeState: MutableLiveData<HomeUiModel> = MutableLiveData()
    private val _isExpanded: MutableLiveData<Boolean> = MutableLiveData(false)


    //    the variable that is exposed to the UI and passed to the binding adapter
    val homeState: LiveData<HomeUiModel> = _homeState
    val isExpanded: LiveData<Boolean> = _isExpanded




}