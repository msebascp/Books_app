package com.sebas.booksapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
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
	val book: LiveData<BookDetail> get() = _book

	fun getBook(bookId: String, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.getBook(token, bookId)
				_book.value = response.data
			} catch (e: Exception) {
				Log.e("Error GetBook", "Response: $e")
				Log.e("Error GetBook", "Error: ${e.message}")
			}
		}
	}

	fun addReadBook(bookId: String, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.storeReadListBook(token, bookId)
				_book.value = response.data
			} catch (e: Exception) {
				Log.e("BookDetail", "Error: ${e.message}")
			}
		}
	}

	fun deleteReadBook(bookId: String, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.deleteReadListBook(token, bookId)
				_book.value = response.data
			} catch (e: Exception) {
				Log.e("BookDetail", "Error: ${e.message}")
			}
		}
	}

	fun likeBook(bookId: String, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.likeReadListBook(token, bookId)
				_book.value = response.data
				Log.d("BookDetail", "response: $response")
			} catch (e: Exception) {
				Log.e("BookDetail", "Error: ${e.message}")
			}
		}
	}

	fun unlikeBook(bookId: String, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.unlikeReadListBook(token, bookId)
				_book.value = response.data
				Log.d("BookDetail", "response: $response")
			} catch (e: Exception) {
				Log.e("BookDetail", "Error: ${e.message}")
			}
		}
	}

	fun addWatchListBook(bookId: String, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.storeWatchListBook(token, bookId)
				_book.value = response.data
				Log.d("BookDetail", "response: $response")
			} catch (e: Exception) {
				Log.e("BookDetail", "Error: ${e.message}")
			}
		}
	}

	fun deleteWatchListBook(bookId: String, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.deleteWatchListBook(token, bookId)
				_book.value = response.data
				Log.d("BookDetail", "response: $response")
			} catch (e: Exception) {
				Log.e("BookDetail", "Error: ${e.message}")
			}
		}
	}

	fun addCollectionListBook(bookId: String, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.storeCollectionListBook(token, bookId)
				_book.value = response.data
				Log.d("BookDetail", "response: $response")
			} catch (e: Exception) {
				Log.e("BookDetail", "Error: ${e.message}")
			}
		}
	}

	fun deleteCollectionListBook(bookId: String, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.deleteCollectionListBook(token, bookId)
				_book.value = response.data
				Log.d("BookDetail", "response: $response")
			} catch (e: Exception) {
				Log.e("BookDetail", "Error: ${e.message}")
			}
		}
	}
}