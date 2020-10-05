package com.example.network.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import org.koin.dsl.module

val firebaseModule = module {
    single { FirebaseDatabase.getInstance() }
    single { FirebaseStorage.getInstance() }
    single { Firebase.firestore }
}