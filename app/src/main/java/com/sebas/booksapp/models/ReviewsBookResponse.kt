package com.sebas.booksapp.models

data class ReviewsBookResponse(
	val `data`: List<ReviewDetail>,
	val message: String,
	val success: Boolean
)