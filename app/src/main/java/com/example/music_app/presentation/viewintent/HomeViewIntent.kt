package com.example.music_app.presentation.viewintent

sealed class HomeViewIntent {
    object FetchGenreList : HomeViewIntent()
}