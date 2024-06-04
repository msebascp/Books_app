package com.sebas.booksapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sebas.booksapp.viewmodels.LoginViewModel
import com.sebas.booksapp.views.components.EmailTextField
import com.sebas.booksapp.views.components.IconApp
import com.sebas.booksapp.views.components.LoadingScreen

@Composable
fun LoginScreen(
	navController: NavController,
	loginViewModel: LoginViewModel = viewModel()
) {
	val isLoading = loginViewModel.isLoading.observeAsState(true)
	val context = LocalContext.current
	LaunchedEffect(navController) {
		loginViewModel.checkToken(navController, context)
	}
	val scope = rememberCoroutineScope()
	val snackbarHostState = remember { SnackbarHostState() }
	val loginError: Boolean by loginViewModel.loginError.observeAsState(false)
	LaunchedEffect(loginError) {
		if (loginError) {
			snackbarHostState.showSnackbar("Error al iniciar sesión")
			loginViewModel.changeLoginError()
		}
	}
	Scaffold(
		snackbarHost = {
			SnackbarHost(hostState = snackbarHostState)
		},
		content = {
			if (isLoading.value) {
				LoadingScreen()
			} else {
				Login(
					modifier = Modifier.padding(it),
					loginViewModel = loginViewModel,
					navController = navController
				)
			}
		},
		containerColor = MaterialTheme.colorScheme.surface
	)
}

@Composable
fun Login(
	modifier: Modifier = Modifier,
	loginViewModel: LoginViewModel,
	navController: NavController,
) {
	val email: String by loginViewModel.email.observeAsState("")
	val password: String by loginViewModel.password.observeAsState("")
	val loginEnabled: Boolean by loginViewModel.loginEnabled.observeAsState(false)
	val passwordVisible: Boolean by loginViewModel.passwordVisible.observeAsState(false)
	val context = LocalContext.current

	Column(
		modifier = modifier
			.fillMaxHeight()
			.padding(horizontal = 16.dp)
			.statusBarsPadding()
			.verticalScroll(rememberScrollState())
			.safeDrawingPadding(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		IconApp()
		Spacer(modifier = Modifier.padding(10.dp))
		EmailTextField(email) { loginViewModel.onLoginChange(it, password) }
		Spacer(modifier = Modifier.padding(10.dp))
		PasswordTextField(
			password,
			passwordVisible,
			{ loginViewModel.onLoginChange(email, it) },
			{ loginViewModel.onPasswordVisibleChange(!passwordVisible) }
		)
		Spacer(modifier = Modifier.padding(10.dp))
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
		) {
			RegisterLink(navController)
			ForgotPassword(navController)
		}
		Spacer(modifier = Modifier.padding(10.dp))
		LoginButton(loginEnabled) {
			loginViewModel.login(navController, context)
		}
	}
}

@Composable
fun PasswordTextField(
	password: String,
	passwordVisible: Boolean,
	onPasswordChange: (String) -> Unit,
	onVisibilityChange: () -> Unit
) {
	val visualTransformation =
		if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
	OutlinedTextField(
		value = password,
		onValueChange = { onPasswordChange(it) },
		label = { Text(text = "Contraseña") },
		modifier = Modifier.fillMaxWidth(),
		keyboardOptions = KeyboardOptions(
			keyboardType = KeyboardType.Password,
			imeAction = ImeAction.Done
		),
		singleLine = true,
		visualTransformation = visualTransformation,
		trailingIcon = {
			if (password.isNotEmpty()) {
				PasswordVisibleIcon(passwordVisible) { onVisibilityChange() }
			}
		}
	)
}

@Composable
fun PasswordVisibleIcon(passwordVisible: Boolean, onClick: () -> Unit) {
	val icon = if (passwordVisible) Icons.Default.VisibilityOff
	else Icons.Default.Visibility
	IconButton(onClick = { onClick() }) {
		Icon(
			imageVector = icon,
			contentDescription = "Toggle password visibility"
		)
	}
}

@Composable
fun RegisterLink(navController: NavController) {
	TextButton(
		onClick = { navController.navigate("registerScreen") }
	) {
		Text(
			text = "Regístrate aquí",
			color = MaterialTheme.colorScheme.onSurface
		)
	}
}

@Composable
fun ForgotPassword(navController: NavController) {
	TextButton(
		onClick = { navController.navigate("forgotPasswordScreen") }
	) {
		Text(
			text = "¿Olvidaste tu contraseña?",
			color = MaterialTheme.colorScheme.onSurface
		)
	}
}

@Composable
fun LoginButton(loginEnabled: Boolean, onLoginClick: () -> Unit) {
	FilledTonalButton(
		enabled = loginEnabled,
		onClick = { onLoginClick() },
		modifier = Modifier.fillMaxWidth(),
		contentPadding = PaddingValues(16.dp)
	) {
		Text(text = "Iniciar Sesión")
	}
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
	LoginScreen(
		loginViewModel = LoginViewModel(),
		navController = NavController(LocalContext.current)
	)
}
