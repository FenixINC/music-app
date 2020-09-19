package com.example.music_app.presentation.model

data class SongModel(
    var id: Int? = 0,
    var name: String? = "",
    var artist: String? = "",
    var genre: String? = "",
    var album: String? = "",
    var image: Int? = 0,
    var imageUrl: String? = "",
    var songUrl: String? = "",
    var isPlay: Boolean? = false
) : ListItem