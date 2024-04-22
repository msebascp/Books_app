package com.sebas.booksapp.models.responses

data class BooksResponse(
	val `data`: List<Book>,
	val message: String,
	val success: Boolean
)