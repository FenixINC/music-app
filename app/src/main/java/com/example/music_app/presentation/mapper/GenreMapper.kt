package com.example.music_app.presentation.mapper

import com.example.music_app.presentation.model.GenreModel
import com.example.network.response.GenreResponse

fun mapResponseToModel(genreListResponse: List<GenreResponse>) =
    genreListResponse.map { genreResponse ->
        GenreModel(
            id = genreResponse.id,
            genreName = genreResponse.genreName,
            imageUrl = genreResponse.imageUrl,
            bandList = mapResponseToModel(genreResponse.bandList)
        )
    }