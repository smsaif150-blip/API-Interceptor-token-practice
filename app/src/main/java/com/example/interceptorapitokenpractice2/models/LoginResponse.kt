package com.example.interceptorapitokenpractice2.models

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val username: String
)
