package com.sebas.booksapp.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sebas.booksapp.R.drawable.icon_books
import com.sebas.booksapp.viewmodels.LoginViewModel
import com.sebas.booksapp.views.components.EmailTextField
import com.sebas.booksapp.views.components.PasswordTextField

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
	Box(
		Modifier.fillMaxSize()
	) {
		Login(Modifier.align(Alignment.Center), loginViewModel, navController)
	}
}

@Composable
fun Login(
	modifier: Modifier = Modifier,
	loginViewModel: LoginViewModel,
	navController: NavController
) {
	val email: String by loginViewModel.email.observeAsState("")
	val password: String by loginViewModel.password.observeAsState("")
	val loginEnabled: Boolean by loginViewModel.loginEnabled.observeAsState(false)
	val passwordVisible: Boolean by loginViewModel.passwordVisible.observeAsState(false)
	val loginError: Boolean by loginViewModel.loginError.observeAsState(false)

	Column(
		modifier = modifier
			.padding(horizontal = 16.dp)
			.statusBarsPadding()
			.verticalScroll(rememberScrollState())
			.safeDrawingPadding(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		IconApp()
		EmailTextField(email) { loginViewModel.onLoginChange(it, password) }
		PasswordTextField(
			password,
			passwordVisible,
			{ loginViewModel.onLoginChange(email, it) },
			{ loginViewModel.onPasswordVisibleChange(!passwordVisible) }
		)
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
		) {
			RegisterLink()
			ForgotPassword()
		}
		LoginButton(loginEnabled) {
			loginViewModel.login(navController)
		}
	}
	if (loginError) {
		Toast.makeText(LocalContext.current, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
		loginViewModel.changeLoginError()
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
	LoginScreen(
		loginViewModel = LoginViewModel(),
		navController = NavController(LocalContext.current)
	)
}
