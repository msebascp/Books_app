package com.sebas.booksapp.network

import com.sebas.booksapp.models.BasicResponse
import com.sebas.booksapp.models.BookResponse
import com.sebas.booksapp.models.BooksResponse
import com.sebas.booksapp.models.CommentRequest
import com.sebas.booksapp.models.CommentResponse
import com.sebas.booksapp.models.LoginRequest
import com.sebas.booksapp.models.LoginResponse
import com.sebas.booksapp.models.ReadBookResponse
import com.sebas.booksapp.models.RegisterRequest
import com.sebas.booksapp.models.RegisterResponse
import com.sebas.booksapp.models.ReviewRequest
import com.sebas.booksapp.models.ReviewsUserResponse
import com.sebas.booksapp.models.UserResponse
import com.sebas.booksapp.models.UsersResponse

class ApiRepository {
	private val apiService = RetrofitInstance.apiService

	// Rutas Auth
	suspend fun login(email: String, password: String): LoginResponse {
		return apiService.login(LoginRequest(email, password))
	}

	suspend fun register(registerRequest: RegisterRequest): RegisterResponse {
		return apiService.register(registerRequest)
	}

	suspend fun checkToken(token: String): LoginResponse {
		return apiService.checkToken("Bearer $token")
	}

	suspend fun logout(token: String): BasicResponse {
		return apiService.logout("Bearer $token")
	}

	suspend fun forgotPassword(email: String): BasicResponse {
		return apiService.forgotPassword(email)
	}

	// Rutas User

	suspend fun getUser(token: String, userId: String? = ""): UserResponse {
		if (userId == "" || userId == null) {
			return apiService.getUser("Bearer $token")
		}
		return apiService.getUser("Bearer $token", userId)
	}

	suspend fun followUser(token: String, userId: Int): UserResponse {
		return apiService.followUser("Bearer $token", userId)
	}

	suspend fun unfollowUser(token: String, userId: Int): UserResponse {
		return apiService.unfollowUser("Bearer $token", userId)
	}

	suspend fun getFollowers(token: String, userId: String): UsersResponse {
		return apiService.getFollowers("Bearer $token", userId)
	}

	suspend fun getFollowing(token: String, userId: String): UsersResponse {
		return apiService.getFollowing("Bearer $token", userId)
	}

	suspend fun searchUsers(token: String, username: String): UsersResponse {
		return apiService.searchUsers("Bearer $token", username)
	}

	// Rutas Book
	suspend fun getBooks(token: String): BooksResponse {
		return apiService.getBooks("Bearer $token")
	}

	suspend fun getBook(token: String, bookId: String): BookResponse {
		return apiService.getBook("Bearer $token", bookId)
	}

	suspend fun searchBooks(token: String, bookName: String): BooksResponse {
		return apiService.searchBooks("Bearer $token", bookName)
	}

	// Rutas ReadBook
	suspend fun getReadBooks(token: String, userId: String?): BooksResponse {
		if (userId == null || userId == "") {
			return apiService.getReadBooks("Bearer $token")

		}
		return apiService.getReadBooks("Bearer $token", userId)
	}

	suspend fun getReadBook(token: String, bookId: String, userId: String?): ReadBookResponse {
		if (userId == null || userId == "") {
			return apiService.getReadBook("Bearer $token", bookId)
		}
		return apiService.getReadBook("Bearer $token", bookId, userId)
	}

	suspend fun storeReadListBook(token: String, bookId: String): BookResponse {
		return apiService.storeReadListBook("Bearer $token", bookId)
	}

	suspend fun deleteReadListBook(token: String, bookId: String): BookResponse {
		return apiService.deleteReadListBook("Bearer $token", bookId)
	}

	suspend fun likeReadListBook(token: String, bookId: String): BookResponse {
		return apiService.likeReadListBook("Bearer $token", bookId)
	}

	suspend fun unlikeReadListBook(token: String, bookId: String): BookResponse {
		return apiService.unlikeReadListBook("Bearer $token", bookId)
	}

	suspend fun getLikeBooks(token: String): BooksResponse {
		return apiService.getLikeBooks("Bearer $token")
	}

	suspend fun getWatchList(token: String, userId: String?): BooksResponse {
		if (userId == null || userId == "") {
			return apiService.getWatchList("Bearer $token")
		}
		return apiService.getWatchList("Bearer $token", userId)
	}

	suspend fun getCollectionList(token: String, userId: String?): BooksResponse {
		if (userId == null || userId == "") {
			return apiService.getCollectionList("Bearer $token")
		}
		return apiService.getCollectionList("Bearer $token", userId)
	}

	suspend fun storeWatchListBook(token: String, bookId: String): BookResponse {
		return apiService.storeWatchListBook("Bearer $token", bookId)
	}

	suspend fun deleteWatchListBook(token: String, bookId: String): BookResponse {
		return apiService.deleteWatchListBook("Bearer $token", bookId)
	}

	suspend fun storeCollectionListBook(token: String, bookId: String): BookResponse {
		return apiService.storeCollectionListBook("Bearer $token", bookId)
	}

	suspend fun deleteCollectionListBook(token: String, bookId: String): BookResponse {
		return apiService.deleteCollectionListBook("Bearer $token", bookId)
	}

	suspend fun getReviews(token: String, userId: String?): ReviewsUserResponse {
		if (userId == null || userId == "") {
			return apiService.getReviewsUser("Bearer $token")
		}
		return apiService.getReviewsUser("Bearer $token", userId)
	}

	suspend fun getReviewsBook(token: String, bookId: String): ReviewsUserResponse {
		return apiService.getReviewsBook("Bearer $token", bookId)
	}

	// Add interaction
	suspend fun addReview(token: String, bookId: String, review: String): BookResponse {
		return apiService.addReview("Bearer $token", ReviewRequest(bookId, review))
	}

	suspend fun addComment(token: String, reviewId: String, comment: String): CommentResponse {
		return apiService.addComment("Bearer $token", CommentRequest(reviewId, comment))
	}
}