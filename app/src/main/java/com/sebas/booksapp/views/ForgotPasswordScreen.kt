package com.sebas.booksapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sebas.booksapp.viewmodels.ForgotPasswordViewModel
import com.sebas.booksapp.views.components.EmailTextField
import com.sebas.booksapp.views.components.IconApp

@Composable
fun ForgotPasswordScreen(
	navController: NavController,
	forgotPasswordViewModel: ForgotPasswordViewModel = viewModel()
) {
	val email: String by forgotPasswordViewModel.email.observeAsState("")
	val context = LocalContext.current

	Column(
		modifier = Modifier
			.fillMaxHeight()
			.fillMaxWidth()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		IconApp(75)
		EmailTextField(
			email = email,
			onEmailChange = { forgotPasswordViewModel.changeEmail(it) }
		)
		TextButton(
			modifier = Modifier.align(Alignment.Start),
			onClick = { navController.navigate("loginScreen") }
		) {
			Text(
				text = "Iniciar sesión",
				color = MaterialTheme.colorScheme.onSurface
			)
		}
		Text(
			modifier = Modifier.padding(bottom = 16.dp),
			text = "Revisa tu correo para restablecer tu contraseña"
		)
		FilledTonalButton(
			modifier = Modifier.fillMaxWidth(),
			onClick = { forgotPasswordViewModel.sendEmail(context) }) {
			Text(text = "Enviar")
		}
	}
}