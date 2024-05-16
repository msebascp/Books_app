package com.sebas.booksapp.network

import com.sebas.booksapp.models.BasicResponse
import com.sebas.booksapp.models.BookResponse
import com.sebas.booksapp.models.BooksResponse
import com.sebas.booksapp.models.LoginRequest
import com.sebas.booksapp.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
	@GET("books")
	suspend fun getBooks(@Header("Authorization") token: String): BooksResponse

	@POST("login")
	suspend fun login(@Body request: LoginRequest): LoginResponse

	@GET("check_token")
	suspend fun checkToken(@Header("Authorization") token: String): LoginResponse

	@GET("books/{bookId}")
	suspend fun getBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@GET("readlists")
	suspend fun getReadList(@Header("Authorization") token: String): BooksResponse

	@GET("readlists/{bookId}")
	suspend fun storeReadListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BasicResponse

	@DELETE("readlists/{bookId}")
	suspend fun deleteReadListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BasicResponse

	@GET("readlists/like/{bookId}")
	suspend fun likeReadListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@DELETE("readlists/like/{bookId}")
	suspend fun unlikeReadListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BookResponse

	@GET("like_books")
	suspend fun getLikeBooks(@Header("Authorization") token: String): BookResponse

	@GET("watchlists")
	suspend fun getWatchList(@Header("Authorization") token: String): BooksResponse

	@GET("watchlists/{bookId}")
	suspend fun storeWatchListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BasicResponse

	@DELETE("watchlists/{bookId}")
	suspend fun deleteWatchListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BasicResponse

	@GET("collectionlists")
	suspend fun getCollectionList(@Header("Authorization") token: String): BooksResponse

	@GET("collectionlists/{bookId}")
	suspend fun storeCollectionListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BasicResponse

	@DELETE("collectionlists/{bookId}")
	suspend fun deleteCollectionListBook(
		@Header("Authorization") token: String,
		@Path("bookId") bookId: String
	): BasicResponse
}