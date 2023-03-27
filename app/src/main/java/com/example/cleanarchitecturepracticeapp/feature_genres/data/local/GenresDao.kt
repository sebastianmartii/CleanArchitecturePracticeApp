package com.example.cleanarchitecturepracticeapp.feature_genres.data.local

import androidx.room.*
import com.example.cleanarchitecturepracticeapp.feature_genres.data.local.entity.GenresEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genres: GenresEntity)

    @Delete
    suspend fun deleteGenres(genres: List<GenresEntity>)

    @Query("SELECT * FROM genres_table ORDER BY id DESC")
    fun getGenres(): Flow<List<GenresEntity>>

}