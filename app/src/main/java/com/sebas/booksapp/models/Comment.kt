package com.sebas.booksapp.models

data class Comment(
	val content: String,
	val created_at: String,
	val id: Int,
	val review_id: Int,
	val updated_at: String,
	val user_id: Int,
	val user: User
)