package com.sebas.booksapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sebas.booksapp.viewmodels.RegisterViewModel
import com.sebas.booksapp.views.components.IconApp

@Composable
fun RegisterScreen(
	navController: NavController,
	registerViewModel: RegisterViewModel = viewModel()
) {
	val context = LocalContext.current
	val name: String by registerViewModel.name.observeAsState("")
	val username: String by registerViewModel.username.observeAsState("")
	val email: String by registerViewModel.email.observeAsState("")
	val password: String by registerViewModel.password.observeAsState("")
	val confirmPassword: String by registerViewModel.confirmPassword.observeAsState("")
	val registerEnabled: Boolean by registerViewModel.registerEnabled.observeAsState(false)
	val usernameError: Boolean by registerViewModel.usernameError.observeAsState(false)
	val emailError: Boolean by registerViewModel.emailError.observeAsState(false)
	val usernameLabel: String by registerViewModel.usernameLabel.observeAsState("Nombre de usuario")
	val emailLabel: String by registerViewModel.emailLabel.observeAsState("Email")

	LazyColumn(
		modifier = Modifier
			.fillMaxHeight()
			.fillMaxWidth()
			.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		item {
			IconApp(75)
		}
		item {
			OutlinedTextField(
				modifier = Modifier.fillMaxWidth(),
				value = name,
				onValueChange = {
					registerViewModel.onRegisterChange(
						name = it,
						password = password,
						confirmPassword = confirmPassword,
						email = email,
						username = username
					)
				},
				label = { Text("Nombre") },
				singleLine = true,
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Next
				)
			)
		}
		item {
			OutlinedTextField(
				modifier = Modifier.fillMaxWidth(),
				value = username,
				onValueChange = {
					registerViewModel.onRegisterChange(
						name = name,
						password = password,
						confirmPassword = confirmPassword,
						email = email,
						username = it
					)
				},
				label = { Text(usernameLabel) },
				singleLine = true,
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Next
				),
				isError = usernameError,
			)
		}
		item {
			OutlinedTextField(
				modifier = Modifier.fillMaxWidth(),
				value = email,
				onValueChange = {
					registerViewModel.onRegisterChange(
						name = name,
						password = password,
						confirmPassword = confirmPassword,
						email = it,
						username = username
					)
				},
				label = { Text(emailLabel) },
				singleLine = true,
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Next
				),
				isError = emailError,
			)
		}
		item {
			OutlinedTextField(
				modifier = Modifier.fillMaxWidth(),
				value = password,
				onValueChange = {
					registerViewModel.onRegisterChange(
						name = name,
						password = it,
						confirmPassword = confirmPassword,
						email = email,
						username = username
					)
				},
				label = { Text("Contraseña") },
				singleLine = true,
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Next
				)
			)
		}
		item {
			OutlinedTextField(
				modifier = Modifier.fillMaxWidth(),
				value = confirmPassword,
				onValueChange = {
					registerViewModel.onRegisterChange(
						name = name,
						password = password,
						confirmPassword = it,
						email = email,
						username = username
					)
				},
				label = { Text("Confirmar contraseña") },
				singleLine = true,
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Done
				)
			)
		}
		item {
			TextButton(
				onClick = {
					navController.navigate("loginScreen")
				}
			) {
				Text(
					text = "¿Ya tienes una cuenta? Inicia sesión",
					color = MaterialTheme.colorScheme.onSurface
				)
			}
		}
		item {
			FilledTonalButton(
				modifier = Modifier.fillMaxWidth(),
				onClick = {
					registerViewModel.register(navController, context)
				},
				enabled = registerEnabled
			) {
				Text("Registrarse")
			}
		}
	}
}