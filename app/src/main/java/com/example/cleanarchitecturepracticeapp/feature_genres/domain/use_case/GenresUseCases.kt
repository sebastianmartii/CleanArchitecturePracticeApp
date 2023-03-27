package com.example.cleanarchitecturepracticeapp.feature_genres.domain.use_case

data class GenresUseCases(
    val getGenre: GetGenre,
    val getAllGenres: GetAllGenres,
    val deleteGenres: DeleteGenres
)
