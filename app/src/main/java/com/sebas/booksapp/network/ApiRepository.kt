package com.sebas.booksapp.network

import com.sebas.booksapp.models.BookResponse
import com.sebas.booksapp.models.BooksResponse
import com.sebas.booksapp.models.LoginRequest
import com.sebas.booksapp.models.LoginResponse
import com.sebas.booksapp.models.ReviewRequest
import com.sebas.booksapp.models.ReviewsResponse

class ApiRepository {
	private val apiService = RetrofitInstance.apiService

	suspend fun getBooks(token: String): BooksResponse {
		return apiService.getBooks("Bearer $token")
	}

	suspend fun login(email: String, password: String): LoginResponse {
		return apiService.login(LoginRequest(email, password))
	}

	suspend fun checkToken(token: String): LoginResponse {
		return apiService.checkToken("Bearer $token")
	}

	suspend fun getBook(token: String, bookId: String): BookResponse {
		return apiService.getBook("Bearer $token", bookId)
	}

	suspend fun getReadList(token: String): BooksResponse {
		return apiService.getReadList("Bearer $token")
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

	suspend fun getWatchList(token: String): BooksResponse {
		return apiService.getWatchList("Bearer $token")
	}

	suspend fun getCollectionList(token: String): BooksResponse {
		return apiService.getCollectionList("Bearer $token")
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

	suspend fun addReview(token: String, bookId: String, review: String): BookResponse {
		return apiService.addReview("Bearer $token", ReviewRequest(bookId, review))
	}

	suspend fun getReviews(token: String): ReviewsResponse {
		return apiService.getReviewsUser("Bearer $token")
	}

	suspend fun getReviewsBook(token: String, bookId: String): ReviewsResponse {
		return apiService.getReviewsBook("Bearer $token", bookId)
	}
}