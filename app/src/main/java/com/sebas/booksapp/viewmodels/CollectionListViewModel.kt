package com.sebas.booksapp.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.booksapp.AuthStore
import com.sebas.booksapp.models.Book
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class CollectionListViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _books = MutableLiveData<List<Book>>()
	val books: LiveData<List<Book>> get() = _books

	private val _isLoading = MutableLiveData<Boolean>()
	val isLoading: LiveData<Boolean> get() = _isLoading

	fun getBooks(context: Context, userId: String? = "") {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.getCollectionList(token, userId)
				_books.value = response.data
			} catch (e: Exception) {
				_books.value = emptyList()
			} finally {
				_isLoading.value = false
			}
		}
	}
}