package com.example.cleanarchitecturepracticeapp.feature_genres.presentation

import com.example.cleanarchitecturepracticeapp.feature_genres.data.local.entity.GenresEntity

data class GenreState(
    val genre: String? = null,
    val genres: List<GenresEntity> = emptyList(),
    val isLoading: Boolean = false
)
