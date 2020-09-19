package com.example.music_app.presentation.model

data class GenreModel(
    var genre: String? = "",
    var songList: List<ListItem>? = arrayListOf()
) : ListItem