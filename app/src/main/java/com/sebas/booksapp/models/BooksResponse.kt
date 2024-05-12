package com.sebas.booksapp.models

data class BooksResponse(
	val `data`: List<Book>,
	val message: String,
	val success: Boolean
)