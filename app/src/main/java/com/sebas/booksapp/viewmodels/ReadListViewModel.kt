package com.sebas.booksapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.booksapp.AuthStore
import com.sebas.booksapp.models.Book
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class ReadListViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _books = MutableLiveData<List<Book>>()
	val books: MutableLiveData<List<Book>> get() = _books

	private val _isLoading = MutableLiveData<Boolean>()
	val isLoading: LiveData<Boolean> get() = _isLoading

	fun getReadList(context: Context, userId: String?) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.getReadBooks(token, userId)
				_books.value = response.data
			} catch (e: Exception) {
				Log.e("ReadListViewModel", "Error getting read list", e)
			} finally {
				_isLoading.value = false
			}
		}
	}
}