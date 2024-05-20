package com.sebas.booksapp.models

data class ReviewRequest(
	val book_id: String,
	val review: String,
)