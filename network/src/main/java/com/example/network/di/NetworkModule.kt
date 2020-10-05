package com.example.network.di

import com.example.network.repository.MusicRepository
import org.koin.dsl.module

val networkModule = module {
    single { MusicRepository() }
}