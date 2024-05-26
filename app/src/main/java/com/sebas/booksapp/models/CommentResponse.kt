package com.sebas.booksapp.models

data class CommentResponse(
	val `data`: Comment,
	val message: String,
	val success: Boolean
)