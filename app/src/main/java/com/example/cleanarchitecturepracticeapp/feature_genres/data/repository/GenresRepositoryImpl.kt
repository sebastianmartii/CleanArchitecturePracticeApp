package com.example.cleanarchitecturepracticeapp.feature_genres.data.repository

import com.example.cleanarchitecturepracticeapp.core.util.Resource
import com.example.cleanarchitecturepracticeapp.feature_genres.data.local.GenresDao
import com.example.cleanarchitecturepracticeapp.feature_genres.data.local.entity.GenresEntity
import com.example.cleanarchitecturepracticeapp.feature_genres.data.remote.GenresApi
import com.example.cleanarchitecturepracticeapp.feature_genres.domain.repository.GenresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GenresRepositoryImpl(
    private val dao: GenresDao,
    private val genresApi: GenresApi
) : GenresRepository{

    override fun getGenre(): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        val genre = try {
            genresApi.getGenre()
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!"
            ))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!"
            ))
        }

        dao.insertGenre(GenresEntity(genre = genre.toString()))
        emit(Resource.Success(genre.toString()))
    }

    override fun getAllGenres(): Flow<List<GenresEntity>> {
        return dao.getGenres()
    }

    override suspend fun deleteGenres(list: List<GenresEntity>) {
        dao.deleteGenres(list)
    }
}