package com.sebas.booksapp.models.responses

data class LoginResponse(
    val message: String,
    val success: Boolean,
    val token: String
)