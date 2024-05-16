package com.sebas.booksapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sebas.booksapp.models.BookDetail
import com.sebas.booksapp.viewmodels.BookDetailViewModel
import com.sebas.booksapp.views.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
	drawerState: DrawerState,
	navController: NavController,
	bookId: String,
	bookDetailViewModel: BookDetailViewModel = viewModel(),
) {
	val context = LocalContext.current
	bookDetailViewModel.getBook(bookId, context)
	val scope = rememberCoroutineScope()
	val book by bookDetailViewModel.book.observeAsState()
	val sheetState = rememberModalBottomSheetState()
	var showBottomSheet by remember { mutableStateOf(false) }

	Scaffold(
		topBar = {
			TopBar(navController = navController, drawerState, scope, true)
		},
		content = { paddingValues ->
			BookDeatilContent(paddingValues, book)
			if (showBottomSheet) {
				ModalBottomSheet(
					onDismissRequest = {
						showBottomSheet = false
					},
					sheetState = sheetState,
				) {
					SheetContent()
				}
			}
		},
		floatingActionButton = {
			FloatingActionButton(
				onClick = { showBottomSheet = true },
			) {
				Icon(Icons.Filled.Add, "Floating action button.")
			}
		}
	)
}

@Composable
fun BookDeatilContent(paddingValues: PaddingValues, book: BookDetail?) {
	LazyColumn(
		contentPadding = paddingValues
	) {
		item {
			Row(
				modifier = Modifier
					.padding(16.dp)
					.fillMaxWidth(),
			) {
				Column(
					// Evita que el texto alargue la columna y minimice la imagen
					modifier = Modifier
						.weight(1f)
						.padding(end = 16.dp)
						.height(165.dp),
					verticalArrangement = Arrangement.SpaceBetween
				) {
					Column {
						Text(
							book?.name ?: "",
							softWrap = true,
						)
						if (book != null) {
							Text(
								book.authors.joinToString { it.name },
								softWrap = true,
							)
						}
						if (book != null) {
							Text(
								book.categories.joinToString { it.name },
								softWrap = true,
							)
						}
					}
					if (book?.is_read == true) {
						Icon(
							imageVector = Icons.Default.Favorite,
							contentDescription = "Like",
							tint = androidx.compose.ui.graphics.Color.Gray,
						)
					}
				}
				AsyncImage(
					model = book?.image_path,
					contentDescription = "Book cover",
					modifier = Modifier.width(110.dp),
				)
			}
		}
		item {
			Text(
				text = book?.description ?: "",
				modifier = Modifier.padding(16.dp),
				softWrap = true,
			)
		}
	}
}

@Composable
fun SheetContent() {
	Row(
		modifier = Modifier
			.padding(16.dp)
			.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		IconButton(
			onClick = { /*TODO*/ },
		) {
			Icon(
				imageVector = Icons.Default.BookmarkAdd,
				contentDescription = "ReadIcon",
				modifier = Modifier
					.width(80.dp)
					.height(80.dp)
			)
		}
		IconButton(
			onClick = { /*TODO*/ },
		) {
			Icon(
				imageVector = Icons.Default.WatchLater,
				contentDescription = "WatchlistIcon",
				modifier = Modifier
					.width(80.dp)
					.height(80.dp)
			)
		}
		IconButton(
			onClick = { /*TODO*/ },
		) {
			Icon(
				imageVector = Icons.Default.CollectionsBookmark,
				contentDescription = "CollectionlistIcon",
				modifier = Modifier
					.width(80.dp)
					.height(80.dp)
			)
		}
		IconButton(
			onClick = { /*TODO*/ },
		) {
			Icon(
				imageVector = Icons.Default.Favorite,
				contentDescription = "Like",
				modifier = Modifier
					.width(80.dp)
					.height(80.dp)
			)
		}
	}
}