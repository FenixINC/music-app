package com.example.network.response

import com.google.firebase.firestore.PropertyName

data class GenreResponse(
    @get: PropertyName("genre") @set: PropertyName("genre") var genre: String? = "",
    @get: PropertyName("songList") @set: PropertyName("songList") var songList: List<SongResponse>? = arrayListOf(),
)