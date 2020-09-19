package com.example.music_app.presentation.mapper

import com.example.music_app.data.network.response.SongResponse
import com.example.music_app.presentation.model.SongModel

fun mapResponseToModel(songListResponse: List<SongResponse>?) = songListResponse?.map { songResponse ->
    SongModel(
        id = songResponse.id,
        name = songResponse.name,
        songUrl = songResponse.songUrl,
        imageUrl = songResponse.imageUrl
    )
}