package com.example.offlinecaching.domain.model

data class Character(
    val family: String,
    val firstName: String,
    val fullName: String,
    val id: Int,
    val imageUrl: String,
    val lastName: String,
    val title: String
)