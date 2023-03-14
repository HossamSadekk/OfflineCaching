package com.example.offlinecaching.domain.use_case

import com.example.offlinecaching.common.Resource
import com.example.offlinecaching.domain.model.RickAndMorty
import com.example.offlinecaching.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    operator fun invoke(): Flow<Resource<RickAndMorty>> = flow {
        try {
            emit(Resource.Loading())
            val characters = characterRepository.getCharacters()
            emit(Resource.Success(characters))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected Error Just Happened"))
        } catch (e: IOException) {
            emit(Resource.Error("There's No Internet Connection ${e.localizedMessage}"))
        }
    }
}