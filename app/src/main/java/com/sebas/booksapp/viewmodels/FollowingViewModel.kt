package com.sebas.booksapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.booksapp.AuthStore
import com.sebas.booksapp.models.User
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class FollowingViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _following = MutableLiveData<List<User>>()
	val following: LiveData<List<User>> get() = _following

	fun getFollowing(context: Context, userId: String) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.getFollowing(token, userId)
				_following.value = response.data
				Log.d("GetFollowing", "Response: $response")
			} catch (e: Exception) {
				Log.e("Error GetFollowing", "Error: ${e.message}")
			}
		}
	}
}