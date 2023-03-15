package com.example.offlinecaching.data.local

import androidx.room.*
import com.example.offlinecaching.domain.model.Characters
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<Characters>)

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()

    @Query("SELECT * FROM characters")
    fun getAllCharacters():Flow<List<Characters>>
}