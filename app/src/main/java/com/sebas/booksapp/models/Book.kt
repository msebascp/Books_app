package com.sebas.booksapp.models

data class Book(
	val created_at: String,
	val description: String,
	val id: Int,
	val image_path: Any,
	val name: String,
	val updated_at: String
)
