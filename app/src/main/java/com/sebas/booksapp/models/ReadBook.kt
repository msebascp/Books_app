package com.sebas.booksapp.models

data class ReadBook(
	val book: Book,
	val book_id: Int,
	val created_at: String,
	val id: Int,
	val is_like: Int,
	val review: ReviewDetail,
	val updated_at: String,
	val user_id: Int,
	val user: User
)