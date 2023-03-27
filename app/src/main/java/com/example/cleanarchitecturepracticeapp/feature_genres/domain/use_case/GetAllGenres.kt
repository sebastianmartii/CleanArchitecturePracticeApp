package com.example.cleanarchitecturepracticeapp.feature_genres.domain.use_case

import com.example.cleanarchitecturepracticeapp.feature_genres.data.local.entity.GenresEntity
import com.example.cleanarchitecturepracticeapp.feature_genres.domain.repository.GenresRepository
import kotlinx.coroutines.flow.Flow

class GetAllGenres(
    private val repository: GenresRepository
) {
    operator fun invoke(): Flow<List<GenresEntity>> = repository.getAllGenres()
}