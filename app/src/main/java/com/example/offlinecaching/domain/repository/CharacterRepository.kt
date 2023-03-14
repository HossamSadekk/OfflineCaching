package com.example.offlinecaching.domain.repository

import com.example.offlinecaching.domain.model.RickAndMorty

interface CharacterRepository {
    suspend fun getCharacters():RickAndMorty
}