package com.sebas.booksapp.models.responses

data class Book(
	val authors: List<Author>,
	val categories: List<Category>,
	val created_at: String,
	val description: String,
	val id: Int,
	val image_path: Any,
	val name: String,
	val updated_at: String
)