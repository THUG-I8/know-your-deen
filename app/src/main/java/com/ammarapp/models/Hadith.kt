package com.ammarapp.models

data class Hadith(
    val id: Int,
    val title: String,
    val arabicText: String,
    val translation: String,
    val narrator: String,
    val source: String,
    var isFavorite: Boolean
)