package com.sebas.booksapp.models

data class ReviewResponse(
	val `data`: ReviewDetail,
	val message: String,
	val success: Boolean
)