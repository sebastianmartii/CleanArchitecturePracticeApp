package com.example.cleanarchitecturepracticeapp.feature_genres.domain.use_case

import com.example.cleanarchitecturepracticeapp.core.util.Resource
import com.example.cleanarchitecturepracticeapp.feature_genres.domain.repository.GenresRepository
import kotlinx.coroutines.flow.Flow

class GetGenre(
    private val repository: GenresRepository
) {
    operator fun invoke(): Flow<Resource<String>> = repository.getGenre()
}