package com.example.music_app.di

import com.example.music_app.data.repository.HomeRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(value = [ApplicationComponent::class])
object ModuleRepository {

    @Provides
    fun provideHomeRepository(firestoreDb: FirebaseFirestore) = HomeRepository(firestoreDb)
}