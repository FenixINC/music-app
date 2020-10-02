package com.example.music_app.presentation.mapper

import com.example.domain.models.BandDomainModel
import com.example.music_app.presentation.model.BandModel

fun mapDomainToModel(bandListResponse: List<BandDomainModel>?) =
    bandListResponse?.map { bandResponse ->
        BandModel(
            id = bandResponse.id,
            name = bandResponse.name,
            genreName = bandResponse.genreName,
            imageUrl = bandResponse.imageUrl,
            songList = mapDomainToModel(bandResponse.songList)
        )
    }