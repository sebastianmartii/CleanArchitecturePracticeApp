package com.example.cleanarchitecturepracticeapp.feature_genres.domain.repository

import com.example.cleanarchitecturepracticeapp.core.util.Resource
import com.example.cleanarchitecturepracticeapp.feature_genres.data.local.entity.GenresEntity
import kotlinx.coroutines.flow.Flow

interface GenresRepository {

   fun getGenre(): Flow<Resource<String>>

   fun getAllGenres(): Flow<List<GenresEntity>>

   suspend fun deleteGenres(list: List<GenresEntity>)
}