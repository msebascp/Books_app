package com.sebas.booksapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
	private const val BASE_URL = "http://10.0.2.2:8000/api/"

	private val client: OkHttpClient by lazy {
		OkHttpClient.Builder()
			.followRedirects(true)
			.followSslRedirects(true)
			.build()
	}

	val apiService: ApiInterface by lazy {
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(ApiInterface::class.java)
	}
}