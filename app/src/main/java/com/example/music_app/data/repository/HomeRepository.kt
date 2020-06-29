package com.example.music_app.data.repository

import com.example.music_app.data.network.response.SongResponse
import com.example.music_app.presentation.constant.Constants
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepository @Inject constructor(private val firestoreDb: FirebaseFirestore) {

    suspend fun loadSongListFirebase(): Flow<List<SongResponse>> {
        val request = firestoreDb
            .collection(Constants.COLLECTION_SONGS)
            .get()

        return flow {
            val response = request.await().toObjects(SongResponse::class.java)
            emit(value = response)
        }
    }
}