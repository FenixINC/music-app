package com.example.music_app.presentation.mapper

import com.example.domain.models.SongDomainModel
import com.example.music_app.presentation.model.SongModel

fun mapDomainToModel(songListResponse: List<SongDomainModel>?) =
    songListResponse?.map { songResponse ->
        SongModel(
            id = songResponse.id,
            name = songResponse.name,
            songUrl = songResponse.songUrl,
            imageUrl = songResponse.imageUrl
        )
    }