package com.example.offlinecaching.domain.repository

import com.example.offlinecaching.common.Resource
import com.example.offlinecaching.domain.model.Characters
import com.example.offlinecaching.domain.model.RickAndMorty
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
     suspend fun  getCharacters(): Flow<Resource<List<Characters>>>
}