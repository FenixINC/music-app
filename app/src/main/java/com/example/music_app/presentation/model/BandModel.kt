package com.example.music_app.presentation.model

data class BandModel(
    val id: Long? = 0L,
    val name: String? = "",
    val genreName: String? = "",
    val imageUrl: String? = "",
    val songList: List<ListItem>? = arrayListOf()
) : ListItem {
    override val itemId: Long = id ?: 0L
}