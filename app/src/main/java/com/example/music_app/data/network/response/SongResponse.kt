package com.example.music_app.data.network.response

import com.google.firebase.firestore.PropertyName

data class SongResponse(
    @get: PropertyName("id") @set: PropertyName("id") var id: Int? = 0,
    @get: PropertyName("name") @set: PropertyName("name") var name: String? = "",
    @get: PropertyName("genre") @set: PropertyName("genre") var genre: String? = "",
    @get: PropertyName("song_url") @set: PropertyName("song_url") var songUrl: String? = "",
    @get: PropertyName("image_url") @set: PropertyName("image_url") var imageUrl: String? = ""
)