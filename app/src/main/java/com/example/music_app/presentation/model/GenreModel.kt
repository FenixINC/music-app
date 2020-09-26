package com.example.music_app.presentation.model

data class GenreModel(
    val id: Long? = 0L,
    val genreName: String? = "",
    val imageUrl: String? = "",
    val bandList: List<ListItem>? = arrayListOf()
) : ListItem {
    override val itemId: Long = id ?: 0L
}