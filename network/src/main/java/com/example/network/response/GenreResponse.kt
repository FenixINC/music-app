package com.example.network.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("genre_name") val genreName: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("band_list") val bandList: List<BandResponse>
)