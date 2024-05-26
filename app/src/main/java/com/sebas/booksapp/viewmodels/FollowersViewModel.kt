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

class FollowersViewModel : ViewModel() {
	private val repository = ApiRepository()

	private val _followers = MutableLiveData<List<User>>()
	val followers: LiveData<List<User>> get() = _followers

	fun getFollowers(context: Context, userId: String) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.getFollowers(token, userId)
				_followers.value = response.data
				Log.d("GetFollowers", "Response: $response")
			} catch (e: Exception) {
				Log.e("Error GetFollowers", "Error: ${e.message}")
			}
		}
	}
}