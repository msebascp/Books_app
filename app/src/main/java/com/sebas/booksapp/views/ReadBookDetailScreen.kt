package com.sebas.booksapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sebas.booksapp.models.ReadBook
import com.sebas.booksapp.viewmodels.ReadBookDetailViewModel
import com.sebas.booksapp.views.components.CardComment
import com.sebas.booksapp.views.components.CardUserMini
import com.sebas.booksapp.views.components.TopBar

@Composable
fun ReadBookDetailScreen(
	drawerState: DrawerState,
	navController: NavController,
	bookId: String,
	userId: String? = "",
	readBookViewModel: ReadBookDetailViewModel = viewModel(),
) {
	val context = LocalContext.current
	LaunchedEffect(bookId, userId, context) {
		readBookViewModel.getBook(bookId, context, userId)
	}
	val scope = rememberCoroutineScope()
	val readBook: ReadBook? by readBookViewModel.readBook.observeAsState()
	val showComments: Boolean by readBookViewModel.showComments.observeAsState(false)

	Scaffold(
		topBar = {
			TopBar(navController = navController, drawerState, scope, true)
		},
		content = { paddingValues ->
			if (readBook != null) {
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.padding(paddingValues)
				) {
					if (readBook?.review != null) {
						ReadBookButtonsMenu { showComments ->
							readBookViewModel.changeShowComments(showComments)
						}
					}
					if (showComments) {
						ReadBookComments(readBook, navController)
					} else {
						ReadBookDetailContent(readBook)
					}
				}
			}
		},
		floatingActionButton = {
			if (showComments) {
				FloatingActionButton(
					onClick = {
						navController.navigate("addCommentScreen/${readBook?.review?.id}")
					}) {
					Icon(Icons.Filled.Add, "Add comment")
				}
			}
		}
	)
}

@Composable
fun ReadBookButtonsMenu(
	onClickButton: (showComments: Boolean) -> Unit = { }
) {
	Row(
		modifier = Modifier
			.fillMaxWidth(),
	) {
		TextButton(
			onClick = { onClickButton(false) },
			modifier = Modifier.weight(1f),
		) {
			Text(
				text = "Reseña",
				style = MaterialTheme.typography.bodyLarge
			)
		}
		TextButton(
			onClick = { onClickButton(true) },
			modifier = Modifier.weight(1f)
		) {
			Text(
				text = "Comentarios",
				style = MaterialTheme.typography.bodyLarge
			)
		}
	}
}

@Composable
fun ReadBookComments(readBook: ReadBook?, navController: NavController) {
	LazyColumn(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
	) {
		if (readBook?.review?.comments != null) {
			items(items = readBook.review.comments) { comment ->
				CardComment(comment = comment, navController = navController)
				Divider(modifier = Modifier.padding(bottom = 16.dp))
			}
		}
	}
}

@Composable
fun ReadBookDetailContent(
	readBook: ReadBook?
) {
	if (readBook != null) {
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			item {
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Column(
						modifier = Modifier
							.weight(1f)
							.height(165.dp)
							.padding(end = 16.dp),
						verticalArrangement = Arrangement.SpaceAround
					) {
						CardUserMini(user = readBook.user)
						Text(
							text = readBook.book.name,
							style = MaterialTheme.typography.titleLarge
						)
						if (readBook.created_at != null) {
							Text(text = "Leído el ${readBook.created_at}")
						}
					}
					AsyncImage(
						model = readBook.book.image_path,
						contentDescription = "Image of the book",
						modifier = Modifier.width(110.dp)
					)
				}
			}
			if (readBook.review != null) {
				item {
					Text(
						text = "Reseña:",
						style = MaterialTheme.typography.titleLarge,
						modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
					)
					Text(text = readBook.review.content)
				}
			}
		}
	}
}