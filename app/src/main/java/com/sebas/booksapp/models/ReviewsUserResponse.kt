package com.sebas.booksapp.models

data class ReviewsUserResponse(
	val `data`: List<Review>,
	val message: String,
	val success: Boolean
)