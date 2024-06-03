package com.sebas.booksapp.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sebas.booksapp.R
import com.sebas.booksapp.models.Book
import com.sebas.booksapp.models.Comment
import com.sebas.booksapp.models.Review
import com.sebas.booksapp.models.User
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
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = MaterialTheme.colorScheme.surface,
		),
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
fun CardReviewUser(
	navController: NavController,
	review: Review
) {
	Card(
		onClick = {
			navController.navigate("readBookDetailScreen/${review.book_id}")
		},
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 8.dp),
		colors = CardDefaults.cardColors(
			containerColor = Color.Transparent,
		),
		shape = RectangleShape
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

@Composable
fun ButtonItemProfile(ruta: String, navController: NavController, textButton: String) {
	TextButton(
		colors = ButtonDefaults.textButtonColors(
			contentColor = MaterialTheme.colorScheme.onBackground
		),
		onClick = {
			navController.navigate(ruta)
		}
	) {
		Text(textButton)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardBookSearchResult(navController: NavController, book: Book) {
	Card(
		onClick = {
			navController.navigate("bookDetailScreen/${book.id}")
		},
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 8.dp),
		colors = CardDefaults.cardColors(
			containerColor = Color.Transparent,
		),
		shape = RectangleShape
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
		) {
			AsyncImage(
				model = book.image_path,
				contentDescription = "Image of book",
				modifier = Modifier
					.width(80.dp)
					.padding(end = 8.dp)
			)
			Text(
				text = book.name,
			)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardUser(navController: NavController, user: User) {
	Card(
		onClick = {
			navController.navigate("profileScreen?userId=${user.id}")
		},
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 8.dp),
		colors = CardDefaults.cardColors(
			containerColor = Color.Transparent,
		),
		shape = RectangleShape
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
		) {
			if (user.image_profile_path != null) {
				AsyncImage(
					model = user.image_profile_path,
					contentDescription = "Image of user",
					modifier = Modifier
						.padding(end = 8.dp)
						.width(65.dp)
						.clip(CircleShape)
				)
			} else {
				Image(
					modifier = Modifier
						.padding(end = 8.dp)
						.width(65.dp)
						.clip(CircleShape),
					painter = painterResource(
						R.drawable.userprofiledefault,
					),
					contentDescription = "Default profile image"
				)
			}
			Text(
				text = user.name,
			)
		}
	}
}

@Composable
fun CardUserMini(user: User) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 8.dp),
		colors = CardDefaults.cardColors(
			containerColor = Color.Transparent,
		),
		shape = RectangleShape
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
		) {
			if (user.image_profile_path != null) {
				AsyncImage(
					model = user.image_profile_path,
					contentDescription = "Image of user",
					modifier = Modifier
						.padding(end = 8.dp)
						.width(35.dp)
						.clip(CircleShape)
				)
			} else {
				Image(
					modifier = Modifier
						.padding(end = 8.dp)
						.width(35.dp)
						.clip(CircleShape),
					painter = painterResource(
						R.drawable.userprofiledefault,
					),
					contentDescription = "Default profile image"
				)
			}
			Column {
				Text(
					text = user.name,
					style = MaterialTheme.typography.labelLarge
				)
				Text(
					text = user.username,
					style = MaterialTheme.typography.labelSmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardComment(comment: Comment, navController: NavController) {
	Card(
		onClick = {
			navController.navigate("profileScreen?userId=${comment.user.id}")
		},
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 8.dp),
		colors = CardDefaults.cardColors(
			containerColor = Color.Transparent,
		),
		shape = RectangleShape
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(8.dp)
		) {
			CardUserMini(user = comment.user)
			Text(
				text = comment.content,
			)
		}
	}
}

@Composable
fun IconApp(width: Int = 150) {
	Image(
		modifier = Modifier.size(width.dp),
		painter = painterResource(id = R.drawable.icon_books),
		contentDescription = "Icon App"
	)
}

