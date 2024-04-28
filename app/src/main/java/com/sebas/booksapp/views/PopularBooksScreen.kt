package com.sebas.booksapp.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sebas.booksapp.viewmodels.PopularBooksViewModel

@Composable
fun PopularBooksScreen(PopularBooksViewModel: PopularBooksViewModel = viewModel()) {
	val books by PopularBooksViewModel.books.observeAsState(emptyList())
	LazyColumn {
		items(books.size) { index ->
			Text(books[index].name)
		}
	}
}