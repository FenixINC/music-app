package com.example.music_app.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SongModel(
    var id: Int? = 0,
    var name: String? = "",
    var artist: String? = "",
    var album: String? = "",
    var image: Int? = 0,
    var imageUrl: String? = "",
    var songUrl: String? = "",
    var isPlay: Boolean? = false
) : Parcelable