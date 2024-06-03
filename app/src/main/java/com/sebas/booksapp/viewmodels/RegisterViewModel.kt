package com.sebas.booksapp.viewmodels

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.sebas.booksapp.AuthStore
import com.sebas.booksapp.models.RegisterRequest
import com.sebas.booksapp.network.ApiRepository
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class RegisterViewModel : ViewModel() {
	val repository = ApiRepository()

	private val _name = MutableLiveData<String>()
	val name: LiveData<String> get() = _name

	private val _username = MutableLiveData<String>()
	val username: LiveData<String> get() = _username

	private val _email = MutableLiveData<String>()
	val email: LiveData<String> get() = _email

	private val _password = MutableLiveData<String>()
	val password: LiveData<String> get() = _password

	private val _confirmPassword = MutableLiveData<String>()
	val confirmPassword: LiveData<String> get() = _confirmPassword

	private val _registerEnabled = MutableLiveData<Boolean>()
	val registerEnabled: LiveData<Boolean> get() = _registerEnabled

	private val _usernameError = MutableLiveData<Boolean>()
	val usernameError: LiveData<Boolean> get() = _usernameError

	private val _emailError = MutableLiveData<Boolean>()
	val emailError: LiveData<Boolean> get() = _emailError

	private val _usernameLabel = MutableLiveData<String>()
	val usernameLabel: LiveData<String> get() = _usernameLabel

	private val _emailLabel = MutableLiveData<String>()
	val emailLabel: LiveData<String> get() = _emailLabel

	fun onRegisterChange(
		email: String,
		password: String,
		confirmPassword: String,
		name: String,
		username: String
	) {
		_email.value = email
		_password.value = password
		_confirmPassword.value = confirmPassword
		_name.value = name
		_username.value = username
		_registerEnabled.value =
			isEmailValid(email) && isPasswordValid(password) && isConfirmPasswordValid(
				password,
				confirmPassword
			) && name.isNotEmpty() && username.isNotEmpty()
	}

	private fun isEmailValid(email: String): Boolean {
		return Patterns.EMAIL_ADDRESS.matcher(email).matches()
	}

	private fun isPasswordValid(password: String): Boolean {
		return password.length >= 8
	}

	private fun isConfirmPasswordValid(password: String, confirmPassword: String): Boolean {
		return password == confirmPassword
	}

	fun register(navController: NavController, context: Context) {
		viewModelScope.launch {
			try {
				val registerRequest = RegisterRequest(
					_email.value ?: "",
					_password.value ?: "",
					_name.value ?: "",
					_username.value ?: ""
				)
				val response = repository.register(registerRequest)
				AuthStore.saveToken(response.token, context)
				Log.d("RegisterViewModel", "Register finished with response: $response")
				navController.navigate("popularBooksScreen")
			} catch (e: HttpException) {
				val errorBody = e.response()?.errorBody()?.string()
				if (errorBody != null) {
					val jsonObject = JSONObject(errorBody)
					val errorField = jsonObject.getString("errors")
					if (errorField.contains("username")) {
						_usernameError.value = true
						_usernameLabel.value = "El nombre de usuario ya existe"
					} else {
						_usernameError.value = false
						_usernameLabel.value = "Nombre de usuario"
					}
					if (errorField.contains("email")) {
						_emailError.value = true
						_emailLabel.value = "El email ya existe"
					} else {
						_emailError.value = false
						_emailLabel.value = "Email"
					}
				}
				Log.e("RegisterViewModel", "Register failed with exception: $e")
			} catch (e: Exception) {
				Log.e("RegisterViewModel", "Register failed with exception: $e")
				Log.e("RegisterViewModel", "Register failed with exception: ${e.localizedMessage}")
			}
		}
	}
}