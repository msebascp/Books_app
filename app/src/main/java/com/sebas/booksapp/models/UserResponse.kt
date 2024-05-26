package com.sebas.booksapp.models

data class UserResponse(
	val `data`: User,
	val message: String,
	val success: Boolean
)