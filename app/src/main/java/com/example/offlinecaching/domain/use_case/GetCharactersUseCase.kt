package com.example.offlinecaching.domain.use_case

import com.example.offlinecaching.common.Resource
import com.example.offlinecaching.domain.model.Characters
import com.example.offlinecaching.domain.model.RickAndMorty
import com.example.offlinecaching.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    operator suspend fun invoke(): Flow<Resource<List<Characters>>> {
        val characters = characterRepository.getCharacters()
        return characters
    }


}