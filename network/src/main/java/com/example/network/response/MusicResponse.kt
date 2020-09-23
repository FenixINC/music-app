package com.example.network.response

import com.google.firebase.firestore.PropertyName

data class MusicResponse(
    @get: PropertyName("id") @set: PropertyName("id") var id: Int? = 0,
    @get: PropertyName("genre") @set: PropertyName("genre") var genre: String? = "",
    @get: PropertyName("songs") @set: PropertyName("songs") var songs: List<SongResponse>? = arrayListOf()
)