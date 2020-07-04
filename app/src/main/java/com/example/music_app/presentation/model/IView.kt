package com.example.music_app.presentation.model

interface IView<S : IState> {
    fun render(state: S)
}