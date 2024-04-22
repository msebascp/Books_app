package com.sebas.booksapp.network

import com.sebas.booksapp.models.responses.BooksResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
	@GET("books")
	suspend fun getBooks(): Response<BooksResponse>
}