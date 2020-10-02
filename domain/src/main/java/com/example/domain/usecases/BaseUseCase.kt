package com.example.domain.usecases

interface BaseUseCase<P> {

    interface Callback {
        fun onSuccess()
        fun onError(throwable: Throwable)
    }

    fun execute(parameter: P, callback: Callback)
}