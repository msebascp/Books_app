package com.sebas.booksapp.models

data class Review(
	val book_id: Int,
	val content: String,
	val created_at: String,
	val id: Int,
	val updated_at: String,
	val user_id: Int
)