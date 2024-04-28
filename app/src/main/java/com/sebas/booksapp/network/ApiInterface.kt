package com.sebas.booksapp.network

import com.sebas.booksapp.models.LoginRequest
import com.sebas.booksapp.models.responses.BooksResponse
import com.sebas.booksapp.models.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
	@GET("books")
	suspend fun getBooks(): BooksResponse

	@POST("login")
	suspend fun login(@Body request: LoginRequest): LoginResponse
}