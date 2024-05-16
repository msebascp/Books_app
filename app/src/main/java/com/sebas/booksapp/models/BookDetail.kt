package com.sebas.booksapp.models

data class BookDetail(
	val authors: List<Author>,
	val categories: List<Category>,
	val created_at: String,
	val description: String,
	val id: Int,
	val image_path: String,
	val is_like: Boolean,
	val is_read: Boolean,
	val in_watchlist: Boolean,
	val in_collectionlist: Boolean,
	val name: String,
	val updated_at: String
)