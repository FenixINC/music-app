package com.example.domain.usecases

import com.example.domain.mappers.mapGenreResponseToDomain
import com.example.domain.models.GenreDomainModel
import com.example.network.repository.MusicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.koin.java.KoinJavaComponent

class LoadGenreListUseCase {

    private val repository by KoinJavaComponent.inject(MusicRepository::class.java)

    suspend fun loadGenreList(
        fileName: String,
        onError: (String) -> Unit,
        onIsLoading: (Boolean) -> Unit,
        onSuccess: (List<GenreDomainModel>) -> Unit
    ) {
        repository.loadGenreListJson(fileName)
            .map { mapGenreResponseToDomain(it) }
            .flowOn(Dispatchers.IO)
            .catch { throwable ->
                throwable.message?.let { errorMessage ->
                    onError(errorMessage)
                }
            }
            .collect {
                onSuccess(it)
            }

        onIsLoading(false)
    }
}