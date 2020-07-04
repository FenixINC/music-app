package com.example.music_app.presentation.model

sealed class HomeIntent : IIntent {
    object RefreshData : HomeIntent()
    object LoadData : HomeIntent()
}