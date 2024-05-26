package com.sebas.booksapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.booksapp.AuthStore
import com.sebas.booksapp.models.ReadBook
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class ReadBookDetailViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _readBook = MutableLiveData<ReadBook>()
	val readBook: LiveData<ReadBook> get() = _readBook

	private val _showComments = MutableLiveData<Boolean>()
	val showComments: LiveData<Boolean> get() = _showComments

	fun getBook(bookId: String, context: Context, userId: String? = "") {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.getReadBook(token, bookId, userId)
				_readBook.value = response.data
				Log.d("GetBook", "Book: ${response.data}")
			} catch (e: Exception) {
				Log.e("Error GetBook", "Error: ${e.message}")
			}
		}
	}

	fun changeShowComments(showComments: Boolean) {
		_showComments.value = showComments
	}
}