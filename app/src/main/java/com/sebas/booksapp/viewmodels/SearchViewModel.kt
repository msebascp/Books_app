package com.sebas.booksapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.booksapp.AuthStore
import com.sebas.booksapp.models.Book
import com.sebas.booksapp.models.User
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _booksResults = MutableLiveData<List<Book>>()
	val booksResults: LiveData<List<Book>> = _booksResults

	private val _usersResults = MutableLiveData<List<User>>()
	val usersResults: LiveData<List<User>> = _usersResults

	private val _searchQuery = MutableLiveData<String>()
	val searchQuery: LiveData<String> = _searchQuery

	private val _searchBooksOption = MutableLiveData<Boolean>()
	val searchBooksOption: LiveData<Boolean> = _searchBooksOption

	fun changeSearchQuery(searchQuery: String) {
		_searchQuery.value = searchQuery
	}

	fun changeSearchBooksOption(searchBooksOption: Boolean) {
		_searchBooksOption.value = searchBooksOption
	}

	fun searchBooks(context: Context, bookName: String) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.searchBooks(token, bookName)
				_booksResults.value = response.data
				Log.d("SearchBooks", "searchBooks: $response")
			} catch (e: Exception) {
				Log.e("ERROR SearchBooks", "searchBooks: ${e.message}")
			}
		}
	}

	fun searchUsers(context: Context, username: String) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.searchUsers(token, username)
				_usersResults.value = response.data
				Log.d("SearchUsers", "searchUsers: $response")
			} catch (e: Exception) {
				Log.e("ERROR SearchUsers", "searchUsers: ${e.message}")
			}
		}
	}
}