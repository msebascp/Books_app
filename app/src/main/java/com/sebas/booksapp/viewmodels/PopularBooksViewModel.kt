package com.sebas.booksapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.booksapp.models.responses.Book
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class PopularBooksViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _books = MutableLiveData<List<Book>>()
	val books: LiveData<List<Book>> get() = _books

	init {
		viewModelScope.launch {
			val response = repository.getBooks()
			_books.value = response.data
		}
	}
}