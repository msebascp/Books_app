package com.sebas.booksapp.models

data class UsersResponse(
	val `data`: List<User>,
	val message: String,
	val success: Boolean
)