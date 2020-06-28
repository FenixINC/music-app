package com.example.music_app.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
    var title: String? = "",
    var artist: String? = "",
    var image: Int? = 0
) : Parcelable