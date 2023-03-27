package com.example.cleanarchitecturepracticeapp.feature_genres.data.remote

import retrofit2.http.GET

interface GenresApi {

    @GET("/wp-json/genrenator/v1/genre/1")
    suspend fun getGenre(): String
}