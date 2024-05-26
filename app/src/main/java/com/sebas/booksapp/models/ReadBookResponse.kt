package com.sebas.booksapp.models

data class ReadBookResponse(
	val `data`: ReadBook,
	val message: String,
	val success: Boolean
)