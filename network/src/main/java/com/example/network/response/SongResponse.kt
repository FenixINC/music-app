package com.example.network.response

import com.google.gson.annotations.SerializedName

data class SongResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("band_name") val bandName: String,
    @SerializedName("genre_name") val genreName: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("song_url") val songUrl: String
)