package com.example.music_app.utils

import android.content.Context

class AndroidResourceProvider constructor(
    private val context: Context
) : IResourceProvider {
    override fun string(id: Int) = context.resources.getString(id)
}