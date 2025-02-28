package com.mobileexam.tabingo.data

import com.mobileexam.tabingo.api.CharactersApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val charactersRepository: GetCharacters
}
class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://rickandmortyapi.com/api/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: CharactersApiService by lazy {
        retrofit.create(CharactersApiService::class.java)
    }

    override val charactersRepository: GetCharacters  by lazy {
        CharactersApi(retrofitService)
    }

}