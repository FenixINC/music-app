package com.example.domain.models

data class GenreDomainModel(
    val id: Long? = 0L,
    val genreName: String? = "",
    val imageUrl: String? = "",
    val bandList: List<BandDomainModel>? = arrayListOf()
)