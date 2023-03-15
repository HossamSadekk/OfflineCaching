package com.example.offlinecaching.presentation

import com.example.offlinecaching.domain.model.Characters

data class CharacterState(
    val isLoading: Boolean = false,
    val gotCharacters: List<Characters> = emptyList(),
    val error: String = ""
)
