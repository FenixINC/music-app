package com.example.domain.di

import com.example.domain.usecases.LoadGenreListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { LoadGenreListUseCase() }
}