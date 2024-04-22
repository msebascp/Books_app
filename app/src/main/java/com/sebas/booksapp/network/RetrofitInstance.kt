package com.sebas.booksapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
	private const val BASE_URL = "http://127.0.0.1:8000/api/"

	val api: ApiInterface by lazy {
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(ApiInterface::class.java)
	}
}