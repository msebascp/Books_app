package com.sebas.booksapp.models

data class BookResponse(
	val `data`: BookDetail,
	val message: String,
	val success: Boolean
)