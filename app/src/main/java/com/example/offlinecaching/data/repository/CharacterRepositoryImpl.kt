package com.example.offlinecaching.data.repository

import com.example.offlinecaching.data.remote.CharacterApi
import com.example.offlinecaching.domain.model.RickAndMorty
import com.example.offlinecaching.domain.repository.CharacterRepository
import javax.inject.Inject


class CharacterRepositoryImpl @Inject constructor(private val api: CharacterApi):CharacterRepository {
    override suspend fun getCharacters(): RickAndMorty {
        return api.getAllCharacters()
    }

}