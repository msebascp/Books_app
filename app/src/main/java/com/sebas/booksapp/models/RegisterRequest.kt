package com.sebas.booksapp.models

data class RegisterRequest(
	val email: String,
	val password: String,
	val name: String,
	val username: String
)