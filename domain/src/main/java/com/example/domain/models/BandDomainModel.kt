package com.example.domain.models

data class BandDomainModel(
    val id: Long? = 0L,
    val name: String? = "",
    val genreName: String? = "",
    val imageUrl: String? = "",
    val songList: List<SongDomainModel>? = arrayListOf()
)