package com.sebas.booksapp.network

import com.sebas.booksapp.models.BasicResponse
import com.sebas.booksapp.models.BookResponse
import com.sebas.booksapp.models.BooksResponse
import com.sebas.booksapp.models.CommentRequest
import com.sebas.booksapp.models.CommentResponse
import com.sebas.booksapp.models.LoginRequest
import com.sebas.booksapp.models.LoginResponse
import com.sebas.booksapp.models.ReadBookResponse
import com.sebas.booksapp.models.ReviewRequest
import com.sebas.booksapp.models.ReviewsUserResponse
import com.sebas.booksapp.models.UserResponse
import com.sebas.booksapp.models.UsersResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
	// Rutas Auth
	@POST("login")
	suspend fun login(@Body request: LoginRequest): LoginResponse

	@GET("check_token")
	suspend fun checkToken(@Header("Authorization") token: String): LoginResponse

	@GET("logout")
	suspend fun logout(@Header("Authorization") token: String): BasicResponse

	// Rutas User

	@GET("users/{userId}")
	suspend fun getUser(
		@Header("Authorization") token: String,
		@Path("userId") userId: String = ""
	): UserResponse

	@GET("follows/{userId}")
	suspend fun followUser(
		@Header("Authorization") token: String,
		@Path("userId") userId: Int
	): BasicResponse

	@DELETE("follows/{userId}")
	suspend fun unfollowUser(
		@Header("Authorization") token: String,
		@Path("userId") userId: Int
	): BasicResponse

	@GET("follows/followers/{userId}")
	suspend fun getFollowers(
		@Header("Authorization") token: String,
		@Path("userId") userId: String
	): UsersResponse

	@GET("follows/following/{userId}")
	suspend fun getFollowing(
		@Header("Authorization") token: String,
		@Path("userId") userId: String
	): UsersResponse

	@GET("users/search/{username}")
	suspend fun searchUsers(
		@Header("Authorization") token: String,
		@Path("username") username: String
	): UsersResponse

	// Rutas Book
	@GET("books")
	suspend fun getBooks(@Header("Authorization") token: String): BooksResponse

	@GET("books/{bookId}")
	suspend fun getBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@GET("books/search/{nameBook}")
	suspend fun searchBooks(
		@Header("Authorization") token: String,
		@Path("nameBook") nameBook: String
	): BooksResponse

	// Rutas ReadBook
	@GET("readbooks/user/{userId}")
	suspend fun getReadBooks(
		@Header("Authorization") token: String,
		@Path("userId") userId: String? = ""
	): BooksResponse

	@GET("readbooks/book/{bookId}/{userId}")
	suspend fun getReadBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String,
		@Path("userId") userId: String? = ""
	): ReadBookResponse

	@GET("readbooks/read/{bookId}")
	suspend fun storeReadListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@DELETE("readbooks/read/{bookId}")
	suspend fun deleteReadListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@GET("readbooks/like/{bookId}")
	suspend fun likeReadListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@DELETE("readbooks/like/{bookId}")
	suspend fun unlikeReadListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@GET("like_books")
	suspend fun getLikeBooks(@Header("Authorization") token: String): BooksResponse

	@GET("watchbooks/user/{userId}")
	suspend fun getWatchList(
		@Header("Authorization") token: String,
		@Path("userId") userId: String? = ""
	): BooksResponse

	@GET("watchbooks/{bookId}")
	suspend fun storeWatchListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@DELETE("watchbooks/{bookId}")
	suspend fun deleteWatchListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@GET("collectionbooks/user/{userId}")
	suspend fun getCollectionList(
		@Header("Authorization") token: String,
		@Path("userId") userId: String? = ""
	): BooksResponse


	@GET("collectionbooks/{bookId}")
	suspend fun storeCollectionListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@DELETE("collectionbooks/{bookId}")
	suspend fun deleteCollectionListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@POST("reviews")
	suspend fun addReview(
		@Header("Authorization") token: String,
		@Body request: ReviewRequest
	): BookResponse

	@GET("reviews/user/{userId}")
	suspend fun getReviewsUser(
		@Header("Authorization") token: String,
		@Path("userId") userId: String? = ""
	): ReviewsUserResponse

	@GET("reviews/{bookId}")
	suspend fun getReviewsBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): ReviewsUserResponse

	// Rutas Comment
	@POST("comments")
	suspend fun addComment(
		@Header("Authorization") token: String,
		@Body request: CommentRequest
	): CommentResponse
}