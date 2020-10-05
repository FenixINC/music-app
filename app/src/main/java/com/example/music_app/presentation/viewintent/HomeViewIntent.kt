package com.example.music_app.presentation.viewintent

sealed class HomeViewIntent {
    data class FetchGenreList(val fileName: String) : HomeViewIntent()
}