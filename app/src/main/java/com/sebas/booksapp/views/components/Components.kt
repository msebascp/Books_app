package com.sebas.booksapp.views.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sebas.booksapp.models.Book
import com.sebas.booksapp.models.Review
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
	navController: NavController,
	drawerState: DrawerState,
	scope: CoroutineScope,
	goBack: Boolean = false,
	title: String = ""
) {
	TopAppBar(
		title = {
			Text(text = title)
		},
		navigationIcon = {
			if (goBack) {
				IconButton(onClick = { navController.popBackStack() }) {
					Icon(
						imageVector = Icons.AutoMirrored.Default.ArrowBack,
						contentDescription = "Go back"
					)
				}
			} else {
				IconButton(onClick = { scope.launch { drawerState.open() } }) {
					Icon(
						imageVector = Icons.Default.Menu,
						contentDescription = "Open drawer"
					)
				}
			}
		}
	)
}

@Composable
fun CardImageBook(navController: NavController, book: Book) {
	AsyncImage(
		model = book.image_path, contentDescription = "Image of book",
		modifier = Modifier
			.padding(8.dp)
			.clickable(
				onClick = {
					navController.navigate("bookDetailScreen/${book.id}")
				}
			)
	)
}

@Composable
fun CardImageReadBook(navController: NavController, book: Book, userId: String?) {
	AsyncImage(
		model = book.image_path, contentDescription = "Image of book",
		modifier = Modifier
			.padding(8.dp)
			.clickable(
				onClick = {
					navController.navigate("readBookDetailScreen/${book.id}?userId=${userId}")
				}
			)
	)
}

@Composable
fun LoadingScreen() {
	Column(
		modifier = Modifier
			.fillMaxHeight()
			.fillMaxWidth(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		CircularProgressIndicator()
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardReviewPreview(
	navController: NavController,
	review: Review
) {
	Card(
		onClick = {
			navController.navigate("readBookDetail/${review.book_id}")
		},
		modifier = Modifier.fillMaxWidth()

	) {
		Text(text = review.book.name)
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 8.dp)
		) {
			AsyncImage(
				model = review.book.image_path,
				contentDescription = "Image of book",
				modifier = Modifier
					.width(80.dp)
					.padding(end = 8.dp)
			)
			Text(
				text = review.content,
				maxLines = 4,
				overflow = TextOverflow.Ellipsis,
			)
		}
	}
}



