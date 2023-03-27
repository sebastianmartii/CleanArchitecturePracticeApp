package com.example.cleanarchitecturepracticeapp.feature_genres.domain.use_case

import com.example.cleanarchitecturepracticeapp.feature_genres.data.local.entity.GenresEntity
import com.example.cleanarchitecturepracticeapp.feature_genres.domain.repository.GenresRepository

class DeleteGenres(
    private val repository: GenresRepository
) {
    suspend operator fun invoke(list: List<GenresEntity>) {
        repository.deleteGenres(list)
    }
}