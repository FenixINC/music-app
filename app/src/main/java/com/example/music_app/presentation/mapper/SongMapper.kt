package com.example.music_app.presentation.mapper

import com.example.music_app.presentation.model.SongModel
import com.example.network.response.SongResponse

fun mapResponseToModel(songListResponse: List<SongResponse>?) = songListResponse?.map { songResponse ->
    SongModel(
        id = songResponse.id,
        name = songResponse.name,
        songUrl = songResponse.songUrl,
        imageUrl = songResponse.imageUrl
    )
}