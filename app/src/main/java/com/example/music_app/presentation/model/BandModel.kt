package com.example.music_app.presentation.model

data class BandModel(
    val id: Long? = 0L,
    val name: String? = "",
    val songList: List<SongModel>? = arrayListOf()
) : ListItem {
    override val itemId = id ?: 0L
}