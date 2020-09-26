package com.example.network.response

import com.google.gson.annotations.SerializedName

data class BandResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("genre_name") val genreName: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("song_list") val songList: List<SongResponse>
)