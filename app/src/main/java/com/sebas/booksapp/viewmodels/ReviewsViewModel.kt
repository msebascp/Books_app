package com.sebas.booksapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.booksapp.AuthStore
import com.sebas.booksapp.models.Review
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class ReviewsViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _reviews = MutableLiveData<List<Review>>()
	val reviews: LiveData<List<Review>> get() = _reviews

	private val _isLoading = MutableLiveData<Boolean>()
	val isLoading: LiveData<Boolean> get() = _isLoading

	fun getReviews(context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.getReviews(token)
				_reviews.value = response.data
				Log.d("ReviewsViewModel - getReviews", "${response.data}")
			} catch (e: Exception) {
				_reviews.value = emptyList()
				Log.e("ReviewsViewModel - getReviews", "Error getting reviews", e)
			} finally {
				_isLoading.value = false
			}
		}
	}
}