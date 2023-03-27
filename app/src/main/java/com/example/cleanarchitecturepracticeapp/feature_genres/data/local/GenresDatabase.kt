package com.example.cleanarchitecturepracticeapp.feature_genres.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecturepracticeapp.feature_genres.data.local.entity.GenresEntity

@Database(entities = [GenresEntity::class], version = 1, exportSchema = false)
abstract class GenresDatabase : RoomDatabase() {
    abstract val dao: GenresDao
}