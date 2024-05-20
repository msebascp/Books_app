package com.sebas.booksapp.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sebas.booksapp.models.Book
import com.sebas.booksapp.viewmodels.PopularBooksViewModel
import com.sebas.booksapp.views.components.CardImageBook
import com.sebas.booksapp.views.components.LoadingScreen
import com.sebas.booksapp.views.components.TopBar

@Composable
fun PopularBooksScreen(
	drawerState: DrawerState,
	navController: NavController,
	popularBooksViewModel: PopularBooksViewModel = viewModel(),
) {
	popularBooksViewModel.getBooks(LocalContext.current)
	val isLoading by popularBooksViewModel.isLoading.observeAsState(true)
	val books by popularBooksViewModel.books.observeAsState(emptyList())
	val scope = rememberCoroutineScope()


	Scaffold(
		topBar = {
			TopBar(navController = navController, drawerState, scope, title = "Libros Populares")
		},
		content = { paddingValues ->
			if (isLoading) {
				LoadingScreen()
			} else {
				PopularBooksGrid(books, paddingValues, navController)
			}
		},
	)
}

@Composable
fun PopularBooksGrid(
	books: List<Book>,
	paddingValues: PaddingValues,
	navController: NavController
) {
	LazyVerticalGrid(
		columns = GridCells.Adaptive(110.dp),
		modifier = Modifier
			.padding(paddingValues),
		contentPadding = PaddingValues(6.dp)
	) {
		items(items = books, key = { book -> book.id }) { book ->
			CardImageBook(navController, book)
		}
	}
}