package com.example.offlinecaching.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.offlinecaching.common.Resource
import com.example.offlinecaching.domain.use_case.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    ViewModel() {
    private val _viewState = MutableStateFlow<CharacterState>(CharacterState())
    val viewState = _viewState.asStateFlow()

init {
    getCharacters()
}

    fun getCharacters() = viewModelScope.launch {
        getCharactersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _viewState.value = CharacterState(gotCharacters = result.data?.results ?: emptyList())
                }
                is Resource.Error -> {
                    _viewState.value =
                        CharacterState(error = result.message ?: "Unexpected error happen")
                }
                is Resource.Loading -> {
                    _viewState.value = CharacterState(isLoading = true)
                }
            }
        }.launchIn(this)
    }
}