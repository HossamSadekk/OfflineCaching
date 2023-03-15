package com.example.offlinecaching.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.example.offlinecaching.common.Resource
import com.example.offlinecaching.data.local.CharactersDao
import com.example.offlinecaching.data.local.CharactersDatabase
import com.example.offlinecaching.data.remote.CharacterApi
import com.example.offlinecaching.data.remote.networkBoundResource
import com.example.offlinecaching.domain.model.Characters
import com.example.offlinecaching.domain.model.RickAndMorty
import com.example.offlinecaching.domain.repository.CharacterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterApi,
    private val database: CharactersDatabase
) : CharacterRepository {



    override suspend fun getCharacters() = networkBoundResource(
        // pass in the logic to query data from the database
        query = {
            database.charactersDao().getAllCharacters()
        },
        // pass in the logic to fetch data from the api
        fetch = {
            delay(9000)
            api.getAllCharacters().results
        },

        //pass in the logic to save the result to the local cache
        saveFetchResult = { characters ->
            database.withTransaction {
                database.charactersDao().deleteAllCharacters()
                database.charactersDao().insertCharacters(characters)

            }
        },

        //pass in the logic to determine if the networking call should be made
        shouldFetch = {characters ->
            characters.isEmpty()
        }
    )

}