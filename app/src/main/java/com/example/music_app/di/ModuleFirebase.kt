package com.example.music_app.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

//@Module
//@InstallIn(value = [ApplicationComponent::class])
//object ModuleFirebase {
//
//    @Provides
//    @Singleton
//    fun provideFirestoreDatabase() = Firebase.firestore
//
//    @Provides
//    @Singleton
//    fun provideFirebaseStorage() = FirebaseStorage.getInstance()
//
//    @Provides
//    @Singleton
//    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()
//}