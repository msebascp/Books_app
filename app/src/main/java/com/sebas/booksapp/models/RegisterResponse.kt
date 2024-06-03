package com.sebas.booksapp.models

data class RegisterResponse(
	val message: String,
	val success: Boolean,
	val token: String
)