package com.example.music_app.presentation.mapper

import com.example.music_app.data.network.response.GenreResponse
import com.example.music_app.presentation.model.GenreModel

fun mapResponseToModel(genreListResponse: List<GenreResponse>) =
    genreListResponse.map { genreModel ->
        GenreModel(
            genre = genreModel.genre,
            songList = mapResponseToModel(genreModel.songList)
        )
    }