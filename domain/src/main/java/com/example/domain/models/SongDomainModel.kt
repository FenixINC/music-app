package com.example.domain.models

data class SongDomainModel(
    val id: Long? = 0L,
    val name: String? = "",
    val bandName: String? = "",
    val genreName: String? = "",
    val imageUrl: String? = "",
    val songUrl: String? = ""
)