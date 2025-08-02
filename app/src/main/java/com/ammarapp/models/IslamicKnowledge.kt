package com.ammarapp.models

data class IslamicKnowledge(
    val id: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val icon: String,
    val color: String,
    val content: List<String>
)