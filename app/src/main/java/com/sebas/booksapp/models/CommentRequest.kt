package com.sebas.booksapp.models

data class CommentRequest(
	val review_id: String,
	val comment: String,
)