package com.sebas.booksapp.network

import com.sebas.booksapp.models.LoginRequest
import com.sebas.booksapp.models.responses.BooksResponse
import com.sebas.booksapp.models.responses.LoginResponse

class ApiRepository {
	private val apiService = RetrofitInstance.apiService

	suspend fun getBooks(): BooksResponse {
		return apiService.getBooks()
	}

	suspend fun login(email: String, password: String): LoginResponse {
		return apiService.login(LoginRequest(email, password))
	}
}