package com.mobileexam.tabingo.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mobileexam.tabingo.RickAndMortyApp
import com.mobileexam.tabingo.data.CharacterResponse
import com.mobileexam.tabingo.data.GetCharacters
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CharactersUiState {
    data class Success(val characters: CharacterResponse) : CharactersUiState
    object Error : CharactersUiState
    object Loading : CharactersUiState
}
class CharViewModel(
    private val charactersRepository: GetCharacters
) : ViewModel() {
    var charUiState: CharactersUiState by mutableStateOf(CharactersUiState.Loading)
        private set

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try {
                charUiState = CharactersUiState.Success(
                    charactersRepository.getCharacters()
                )
            } catch (e: IOException) {
                charUiState = CharactersUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RickAndMortyApp)
                val charactersRepository = application.container.charactersRepository
                CharViewModel(charactersRepository = charactersRepository)
            }
        }
    }
}