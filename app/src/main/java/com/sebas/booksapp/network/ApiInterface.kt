package com.sebas.booksapp.network

import com.sebas.booksapp.models.BooksResponse
import com.sebas.booksapp.models.LoginRequest
import com.sebas.booksapp.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
	@GET("books")
	suspend fun getBooks(@Header("Authorization") token: String): BooksResponse

	@POST("login")
	suspend fun login(@Body request: LoginRequest): LoginResponse

	@GET("check_token")
	suspend fun checkToken(@Header("Authorization") token: String): LoginResponse
}