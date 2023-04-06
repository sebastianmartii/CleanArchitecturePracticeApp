package com.example.cleanarchitecturepracticeapp.feature_genres.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecturepracticeapp.constants.GENRES_BASE_URL
import com.example.cleanarchitecturepracticeapp.feature_genres.data.local.GenresDatabase
import com.example.cleanarchitecturepracticeapp.feature_genres.data.remote.GenresApi
import com.example.cleanarchitecturepracticeapp.feature_genres.data.repository.GenresRepositoryImpl
import com.example.cleanarchitecturepracticeapp.feature_genres.domain.repository.GenresRepository
import com.example.cleanarchitecturepracticeapp.feature_genres.domain.use_case.DeleteGenres
import com.example.cleanarchitecturepracticeapp.feature_genres.domain.use_case.GenresUseCases
import com.example.cleanarchitecturepracticeapp.feature_genres.domain.use_case.GetAllGenres
import com.example.cleanarchitecturepracticeapp.feature_genres.domain.use_case.GetGenre
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GenresModule {

    @Provides
    @Singleton
    fun provideGenresUseCases(
        getAllGenres: GetAllGenres,
        deleteGenres: DeleteGenres,
        getGenre: GetGenre
    ): GenresUseCases {
        return GenresUseCases(
            getGenre = getGenre,
            getAllGenres = getAllGenres,
            deleteGenres = deleteGenres
        )
    }

    @Provides
    @Singleton
    fun provideDeleteGenresUseCase(repository: GenresRepository): DeleteGenres {
        return DeleteGenres(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllGenresUseCase(repository: GenresRepository): GetAllGenres {
        return GetAllGenres(repository)
    }

    @Provides
    @Singleton
    fun provideGetGenreUseCase(repository: GenresRepository): GetGenre {
        return GetGenre(repository)
    }

    @Provides
    @Singleton
    fun provideGenresApi(): GenresApi {
        return Retrofit.Builder()
            .baseUrl(GENRES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GenresApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGenresRepository(
        api: GenresApi,
        db: GenresDatabase
    ): GenresRepository {
        return GenresRepositoryImpl(genresApi = api, dao = db.genresDao)
    }

    @Provides
    @Singleton
    fun provideGenresDatabase(
        @ApplicationContext appContext: Context
    ): GenresDatabase {
        return Room.databaseBuilder(
            appContext, GenresDatabase::class.java, "genres_db"
        ).build()
    }
}