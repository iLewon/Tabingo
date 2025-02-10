package com.mobileexam.tabingo.model

import androidx.annotation.DrawableRes

data class CharModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    @DrawableRes val imageRes: Int // Marking image as a drawable resource for clarity
)
