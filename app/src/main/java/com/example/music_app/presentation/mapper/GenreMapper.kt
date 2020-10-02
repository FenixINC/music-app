package com.example.music_app.presentation.mapper

import com.example.domain.models.GenreDomainModel
import com.example.music_app.presentation.model.GenreModel

fun mapDomainToModel(genreListResponse: List<GenreDomainModel>) =
    genreListResponse.map { genreResponse ->
        GenreModel(
            id = genreResponse.id,
            genreName = genreResponse.genreName,
            imageUrl = genreResponse.imageUrl,
            bandList = mapDomainToModel(genreResponse.bandList)
        )
    }