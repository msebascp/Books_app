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

class AddCommentViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _comment = MutableLiveData<String>()
	val comment: LiveData<String> get() = _comment

	fun changeComment(review: String) {
		_comment.value = review
	}

	fun addComment(reviewId: String, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.addComment(token, reviewId, _comment.value!!)
				Log.d("AddComment", "Review added: $response")
			} catch (e: Exception) {
				Log.e("AddComment", "Error: ${e.message}")
			}
		}
	}
}