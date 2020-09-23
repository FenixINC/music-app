package com.example.music_app.presentation.mapper

import com.example.music_app.presentation.model.GenreModel
import com.example.network.response.GenreResponse

fun mapResponseToModel(genreListResponse: List<GenreResponse>) =
    genreListResponse.map { genreModel ->
        GenreModel(
            genre = genreModel.genre,
            songList = mapResponseToModel(genreModel.songList)
        )
    }