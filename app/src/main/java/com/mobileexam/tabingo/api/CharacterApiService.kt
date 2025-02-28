package com.mobileexam.tabingo.api

import com.mobileexam.tabingo.data.CharacterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://rickandmortyapi.com/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface CharactersApiService {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}