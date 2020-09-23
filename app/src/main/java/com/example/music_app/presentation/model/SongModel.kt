package com.example.music_app.presentation.model

data class SongModel(
    val id: Long? = 0L,
    val name: String? = "",
    val artist: String? = "",
    val genre: String? = "",
    val album: String? = "",
    val image: Int? = 0,
    val imageUrl: String? = "",
    val songUrl: String? = "",
    val isPlay: Boolean? = false
) : ListItem {
    override val itemId: Long = id ?: 0L
}