package com.sebas.booksapp.views.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun EmailTextField(email: String, onEmailChange: (String) -> Unit) {
	OutlinedTextField(
		value = email,
		onValueChange = { onEmailChange(it) },
		label = { Text(text = "Email") },
		modifier = Modifier.fillMaxWidth(),
		keyboardOptions = KeyboardOptions(
			keyboardType = KeyboardType.Email,
			imeAction = ImeAction.Next
		),
		singleLine = true
	)
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