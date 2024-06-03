package com.sebas.booksapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class ForgotPasswordViewModel : ViewModel() {
	val repository = ApiRepository()

	private var _email = MutableLiveData<String>("")
	val email: LiveData<String> get() = _email

	fun changeEmail(email: String) {
		_email.value = email
	}

	fun sendEmail(context: Context) {
		viewModelScope.launch {
			try {
				val response = repository.forgotPassword(_email.value!!)
				if (response.success) {
					Log.d("Correct SendEmail", "Email enviado")
				}
			} catch (e: Exception) {
				Log.e("Error SendEmail", e.message!!)
			}
		}
	}
}