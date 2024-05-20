package com.sebas.booksapp.models

data class BookDetail(
	val id: Int,
	val created_at: String,
	val updated_at: String,
	val name: String,
	val image_path: String,
	val is_like: Boolean,
	val is_read: Boolean,
	val in_watchlist: Boolean,
	val in_collectionlist: Boolean,
	val authors: List<Author>,
	val categories: List<Category>,
	val description: String,
)