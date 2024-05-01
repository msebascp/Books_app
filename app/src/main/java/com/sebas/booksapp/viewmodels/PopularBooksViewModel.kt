package com.sebas.booksapp.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.booksapp.AuthStore
import com.sebas.booksapp.models.responses.Book
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class PopularBooksViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _books = MutableLiveData<List<Book>>()
	val books: LiveData<List<Book>> get() = _books

	init {
		viewModelScope.launch {
			val response = repository.getBooks("1|HEC6v7keB7CXzFwThLiFnHNNHizhFwdDVl7npZZDe76d1227")
			_books.value = response.data
		}
	}
}