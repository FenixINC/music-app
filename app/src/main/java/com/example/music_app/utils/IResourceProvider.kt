package com.example.music_app.utils

import androidx.annotation.StringRes

interface IResourceProvider {
    fun string(@StringRes id: Int): String
}