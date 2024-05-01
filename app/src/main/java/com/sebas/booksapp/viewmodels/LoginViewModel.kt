package com.sebas.booksapp.viewmodels

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
	private val repository = ApiRepository()

	private var _email = MutableLiveData<String>()
	val email: LiveData<String> get() = _email

	private var _password = MutableLiveData<String>()
	val password: LiveData<String> get() = _password

	private var _loginEnabled = MutableLiveData<Boolean>()
	val loginEnabled: LiveData<Boolean> get() = _loginEnabled

	private var _passwordVisible = MutableLiveData<Boolean>()
	val passwordVisible: LiveData<Boolean> get() = _passwordVisible

	private var _loginError = MutableLiveData<Boolean>()
	var loginError: LiveData<Boolean> = _loginError

	fun onLoginChange(email: String, password: String) {
		_email.value = email
		_password.value = password
		_loginEnabled.value = isEmailValid(email) && isPasswordValid(password)
	}

	private fun isEmailValid(email: String): Boolean {
		return Patterns.EMAIL_ADDRESS.matcher(email).matches()
	}

	private fun isPasswordValid(password: String): Boolean {
		return password.length >= 8
	}

	fun onPasswordVisibleChange(passwordVisible: Boolean) {
		_passwordVisible.value = passwordVisible
	}

	fun changeLoginError() {
		_loginError.value = false
	}

	fun login(navController: NavController, context: Context) {
		val USER_TOKEN_KEY = stringPreferencesKey("user_token")
		viewModelScope.launch {
			try {
				val response = repository.login(_email.value ?: "", _password.value ?: "")
				Log.d("LoginViewModel", "Login finished with response: $response")
				navController.navigate("popularBooksScreen")
			} catch (e: Exception) {
				Log.e("LoginViewModel", "Login failed with exception: $e")
				_loginError.value = true
			}
		}
	}
}