package com.sebas.booksapp.models

data class User(
	val created_at: Any,
	val email: String,
	val email_verified_at: Any,
	val id: Int,
	val image_profile_path: Any,
	val name: String,
	val updated_at: Any,
	val username: String,
	val isFollowing: Boolean,
	val isMe: Boolean
)