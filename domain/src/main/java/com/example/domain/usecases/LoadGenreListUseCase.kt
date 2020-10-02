package com.example.domain.usecases

import com.example.domain.mappers.mapGenreResponseToDomain
import com.example.domain.models.GenreDomainModel
import com.example.network.repository.MusicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadGenreListUseCase @Inject constructor(private val repository: MusicRepository) {

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