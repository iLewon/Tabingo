package com.mobileexam.tabingo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)

@Serializable
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String, // Added type
    val gender: String,
    val origin: Origin,
    val location: Location,
    @SerialName(value = "image")
    val image: String,
    val episode: List<String>,
    val url: String, // Added url
    val created: String // Added created
)

@Serializable
data class Origin(
    val name: String,
    val url: String
)

@Serializable
data class Location(
    val name: String,
    val url: String
)