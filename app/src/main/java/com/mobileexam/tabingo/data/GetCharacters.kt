package com.mobileexam.tabingo.data

import com.mobileexam.tabingo.api.CharactersApiService

interface GetCharacters {
    suspend fun getCharacters(): CharacterResponse
}
class CharactersApi(
    private val charactersApiService: CharactersApiService
) : GetCharacters {
    override suspend fun getCharacters(): CharacterResponse = charactersApiService.getCharacters()
}