package com.sebas.booksapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.booksapp.AuthStore
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class AddReviewViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _review = MutableLiveData<String>()
	val review: LiveData<String> get() = _review

	fun changeReview(review: String) {
		_review.value = review
	}

	fun addReview(bookId: String, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.addReview(token, bookId, _review.value!!)
				Log.d("AddReview", "Review added: ${response.data}")
			} catch (e: Exception) {
				Log.e("AddReview", "Error: ${e.message}")
			}
		}
	}
}