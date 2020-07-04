package com.example.music_app.presentation.model

data class HomeState(
    val data: List<SongModel>? = mutableListOf(),
    val isLoading: Boolean? = false,
    val errorMessage: String? = ""
) : IState