package com.example.network.repository

import com.example.network.constants.FirebaseConstants
import com.example.network.response.SongResponse
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MusicRepository @Inject constructor(private val firestoreDb: FirebaseFirestore) {

    @ExperimentalCoroutinesApi
    val songFlowData = MutableStateFlow<List<SongResponse>>(emptyList())

    @ExperimentalCoroutinesApi
    suspend fun loadSongList() {
        val request = firestoreDb
            .collection(FirebaseConstants.COLLECTION_MUSIC)
            .get()

        val response = request.await().toObjects(SongResponse::class.java)
        songFlowData.value = response
    }

    suspend fun loadSongListFirebase(): Flow<List<SongResponse>> {
        val request = firestoreDb
            .collection(FirebaseConstants.COLLECTION_MUSIC)
            .get()

        val request2 = firestoreDb
            .document(FirebaseConstants.DOCUMENT_MUSIC_RETRO)
            .collection(FirebaseConstants.COLLECTION_SONGS)
            .get()

        val result = request2.await().toObjects(SongResponse::class.java)
        val s = result

        return flow {
            val response = request.await().toObjects(SongResponse::class.java)
            emit(value = response)
        }
    }

//    suspend fun loadGenreListFirebase() : Flow<List<GenreResponse>> {
//        val request = firestoreDb
//            .collection(FirebaseConstants.COLLECTION_IMAGES)
//            .get()
//    }

    suspend fun retroMusicChangesSubscription(): Flow<List<SongResponse>> {
        var newSongList = mutableListOf<SongResponse>()
        firestoreDb
            .document(FirebaseConstants.DOCUMENT_MUSIC_RETRO)
            .collection(FirebaseConstants.COLLECTION_SONGS)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                querySnapshot?.let {
                    newSongList = it.toObjects(SongResponse::class.java)
                }
            }

        return flow {
            emit(value = newSongList)
        }
    }
}