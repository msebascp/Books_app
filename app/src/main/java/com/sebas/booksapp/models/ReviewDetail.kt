package com.sebas.booksapp.models

data class ReviewDetail(
	val book_id: Int,
	val content: String,
	val created_at: String,
	val id: Int,
	val updated_at: String,
	val user_id: Int,
	val comments: List<Comment>,
	val book: Book,
	val user: User,
)