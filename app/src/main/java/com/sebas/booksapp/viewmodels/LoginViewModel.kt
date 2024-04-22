package com.sebas.booksapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
	private var _email = MutableLiveData<String>()
	val email: LiveData<String> get() = _email

	private var _password = MutableLiveData<String>()
	val password: LiveData<String> get() = _password

	private var _loginEnabled = MutableLiveData<Boolean>()
	val loginEnabled: LiveData<Boolean> get() = _loginEnabled

	private var _passwordVisible = MutableLiveData<Boolean>()
	val passwordVisible: LiveData<Boolean> get() = _passwordVisible

	fun onLoginChange(email: String, password: String) {
		_email.value = email
		_password.value = password
		_loginEnabled.value = isEmailValid(email) && isPasswordValid(password)
	}

	private fun isEmailValid(email: String): Boolean {
		return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
	}

	private fun isPasswordValid(password: String): Boolean {
		return password.length >= 8
	}

	fun onPasswordVisibleChange(passwordVisible: Boolean) {
		_passwordVisible.value = passwordVisible
	}

	fun login() {
		// TODO
	}
}