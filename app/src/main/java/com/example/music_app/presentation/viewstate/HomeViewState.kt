package com.example.music_app.presentation.viewstate

import com.example.music_app.presentation.model.ListItem

sealed class HomeViewState {
    data class LoadingState(val isLoading: Boolean) : HomeViewState()
    data class Genres(val genreList: List<ListItem>) : HomeViewState()
    data class Error(val errorMessage: String?) : HomeViewState()
}