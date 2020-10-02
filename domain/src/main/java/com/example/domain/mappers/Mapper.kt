package com.example.domain.mappers

import com.example.domain.models.BandDomainModel
import com.example.domain.models.GenreDomainModel
import com.example.domain.models.SongDomainModel
import com.example.network.response.BandResponse
import com.example.network.response.GenreResponse
import com.example.network.response.SongResponse

fun mapGenreResponseToDomain(genreResponseList: List<GenreResponse>) =
    genreResponseList.map { genreResponse ->
        GenreDomainModel(
            id = genreResponse.id,
            genreName = genreResponse.genreName,
            imageUrl = genreResponse.imageUrl,
            bandList = mapBandResponseToDomain(genreResponse.bandList)
        )
    }

fun mapBandResponseToDomain(bandListResponse: List<BandResponse>) =
    bandListResponse.map { bandResponse ->
        BandDomainModel(
            id = bandResponse.id,
            name = bandResponse.name,
            genreName = bandResponse.genreName,
            imageUrl = bandResponse.imageUrl,
            songList = mapSongResponseToDomain(bandResponse.songList)
        )
    }

fun mapSongResponseToDomain(songListResponse: List<SongResponse>) =
    songListResponse.map { songModel ->
        SongDomainModel(
            id = songModel.id,
            name = songModel.name,
            bandName = songModel.bandName,
            genreName = songModel.genreName,
            imageUrl = songModel.imageUrl,
            songUrl = songModel.songUrl
        )
    }