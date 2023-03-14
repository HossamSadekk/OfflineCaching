package com.example.offlinecaching.data.remote

import com.example.offlinecaching.domain.model.RickAndMorty
import retrofit2.http.GET

interface CharacterApi {
    @GET("/api/character")
    suspend fun getAllCharacters(): RickAndMorty
}