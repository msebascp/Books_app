package com.sebas.booksapp.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sebas.booksapp.viewmodels.PopularBooksViewModel
import com.sebas.booksapp.views.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularBooksScreen(
	navController: NavController,
	popularBooksViewModel: PopularBooksViewModel = viewModel()
) {
	val books by popularBooksViewModel.books.observeAsState(emptyList())
	popularBooksViewModel.getBooks(LocalContext.current)

	Scaffold(
		topBar = {
			TopBar(navController = navController)
		},
		content = { paddingValues ->
			LazyVerticalGrid(
				columns = GridCells.Adaptive(110.dp),
				modifier = Modifier
					.padding(paddingValues),
				contentPadding = PaddingValues(4.dp)
			) {
				items(items = books, key = { book -> book.id }) { book ->
					AsyncImage(
						modifier = Modifier.padding(8.dp),
						model = book.image_path, contentDescription = "Image of book"
					)
				}
			}
		},
	)
}