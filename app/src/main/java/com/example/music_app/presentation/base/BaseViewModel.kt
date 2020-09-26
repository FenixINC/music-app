package com.example.music_app.presentation.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent, LifecycleObserver {
    protected val _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData = _errorMessageLiveData

    protected val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = _loadingLiveData
}