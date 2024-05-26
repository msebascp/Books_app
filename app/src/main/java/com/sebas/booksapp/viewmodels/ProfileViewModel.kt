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

class ProfileViewModel : ViewModel() {
	val repository = ApiRepository()

	private val _user = MutableLiveData<User>()
	val user: LiveData<User> get() = _user

	fun getUser(context: Context, userId: String? = "") {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.getUser(token)
				_user.value = response.data
				Log.d("ProfileViewModel", "User: $response")
			} catch (e: Exception) {
				Log.e("ProfileViewModel", e.message.toString())
			}
		}
	}

	fun unfollowUser(userId: Int, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.unfollowUser(token, userId)
				Log.d("Unfollow", "User: $response")
			} catch (e: Exception) {
				Log.e("Unfollow", e.message.toString())
			}
		}
	}

	fun followUser(userId: Int, context: Context) {
		viewModelScope.launch {
			try {
				val token = AuthStore.getToken(context)
				val response = repository.followUser(token, userId)
				Log.d("Follow", "User: $response")
			} catch (e: Exception) {
				Log.e("Follow", e.message.toString())
			}
		}
	}
}