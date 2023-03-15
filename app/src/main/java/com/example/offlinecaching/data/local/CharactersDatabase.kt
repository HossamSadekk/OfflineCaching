package com.example.offlinecaching.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.offlinecaching.domain.model.Characters

@Database(entities = [Characters::class], version = 2)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}