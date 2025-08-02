package com.ammarapp.models

data class Dhikr(
    val id: Int,
    val title: String,
    val arabicText: String,
    val translation: String,
    val count: Int,
    var currentCount: Int,
    val description: String
)