package com.sebas.booksapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebas.booksapp.R.drawable.icon_books
import com.sebas.booksapp.viewmodels.LoginViewModel
import com.sebas.booksapp.views.components.EmailTextField
import com.sebas.booksapp.views.components.PasswordTextField
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
	Box(
		Modifier.fillMaxSize()
	) {
		Login(Modifier.align(Alignment.Center), viewModel)
	}
}

@Composable
fun Login(modifier: Modifier = Modifier, viewModel: LoginViewModel) {
	val email: String by viewModel.email.observeAsState("")
	val password: String by viewModel.password.observeAsState("")
	val loginEnabled: Boolean by viewModel.loginEnabled.observeAsState(false)
	val passwordVisible: Boolean by viewModel.passwordVisible.observeAsState(false)
	val coroutineScope = rememberCoroutineScope()

	Column(
		modifier = modifier.padding(horizontal = 16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		IconApp()
		EmailTextField(email) { viewModel.onLoginChange(it, password) }
		PasswordTextField(
			password,
			passwordVisible,
			{ viewModel.onLoginChange(email, it) },
			{ viewModel.onPasswordVisibleChange(!passwordVisible) }
		)
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
		) {
			RegisterLink()
			ForgotPassword()
		}
		LoginButton(loginEnabled) {
			coroutineScope.launch {
				viewModel.login()
			}
		}
	}
}

@Composable
fun IconApp() {
	Image(painter = painterResource(id = icon_books), contentDescription = "Icon App")
}

@Composable
fun RegisterLink() {
	Text(
		text = "Regístrate aquí"
	)
}

@Composable
fun ForgotPassword() {
	Text(
		text = "¿Olvidaste tu contraseña?"
	)
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
	LoginScreen(viewModel = LoginViewModel())
}
