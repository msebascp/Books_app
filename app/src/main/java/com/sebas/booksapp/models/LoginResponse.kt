package com.sebas.booksapp.models

data class LoginResponse(
	val message: String,
	val success: Boolean,
	val token: String
)