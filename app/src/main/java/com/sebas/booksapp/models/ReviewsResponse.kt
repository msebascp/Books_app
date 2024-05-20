package com.sebas.booksapp.models

data class ReviewsResponse(
	val `data`: List<Review>,
	val message: String,
	val success: Boolean
)