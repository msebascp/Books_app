package com.sebas.booksapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.booksapp.AuthStore
import com.sebas.booksapp.models.BookDetail
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class BookDetailViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _book = MutableLiveData<BookDetail>()
	val book: MutableLiveData<BookDetail> get() = _book

	fun getBook(bookId: String, context: Context) {
		viewModelScope.launch {
			try {
				Log.d("BookDetail", "mensaje")
				val token = AuthStore.getToken(context)
				val response = repository.getBook(token, bookId)
				Log.d("BookDetail", "response: $response")
				_book.value = response.data
			} catch (e: Exception) {
				Log.e("BookDetail", "Error: ${e.message}")
			}
		}
	}
}