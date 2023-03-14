package com.example.offlinecaching.presentation

import com.example.offlinecaching.domain.model.Result

data class CharacterState(
    val isLoading: Boolean = false,
    val gotCharacters: List<Result> = emptyList(),
    val error: String = ""
)
