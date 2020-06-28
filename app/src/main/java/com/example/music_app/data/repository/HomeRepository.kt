package com.example.music_app.data.repository

import com.example.music_app.R
import com.example.music_app.presentation.model.Track
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor() {

    suspend fun loadTrackListNetwork() = flow {
        // TODO: change to real network request
        delay(1000)
        emit(
            arrayListOf(
                Track("title_1", "artist_1", R.drawable.t1),
                Track("title_2", "artist_2", R.drawable.t2),
                Track("title_3", "artist_3", R.drawable.t3),
                Track("title_4", "artist_4", R.drawable.t4)
            )
        )
    }
}