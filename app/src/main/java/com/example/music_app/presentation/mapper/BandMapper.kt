package com.example.music_app.presentation.mapper

import com.example.music_app.presentation.model.BandModel
import com.example.network.response.BandResponse

fun mapResponseToModel(bandListResponse: List<BandResponse>) =
    bandListResponse.map { bandResponse ->
        BandModel(
            id = bandResponse.id,
            name = bandResponse.name,
            genreName = bandResponse.genreName,
            imageUrl = bandResponse.imageUrl,
            songList = mapResponseToModel(bandResponse.songList)
        )
    }