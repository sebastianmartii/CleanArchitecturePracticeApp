package com.example.cleanarchitecturepracticeapp.feature_genres.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres_table")
data class GenresEntity(
    @PrimaryKey val id: Int? = null,
    val genre: String
)