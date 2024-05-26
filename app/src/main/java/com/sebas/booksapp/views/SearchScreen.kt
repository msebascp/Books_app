package com.sebas.booksapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sebas.booksapp.models.Book
import com.sebas.booksapp.models.User
import com.sebas.booksapp.viewmodels.SearchViewModel
import com.sebas.booksapp.views.components.TopBar

@Composable
fun SearchScreen(
	drawerState: DrawerState,
	navController: NavController,
	searchViewModel: SearchViewModel = viewModel(),
) {
	val scope = rememberCoroutineScope()
	Scaffold(
		topBar = {
			TopBar(navController = navController, drawerState, scope, true, "Buscar")
		},
		content = { paddingValues ->
			SearchContent(paddingValues, navController, searchViewModel)
		}
	)
}

@Composable
fun SearchContent(
	paddingValues: PaddingValues,
	navController: NavController,
	searchViewModel: SearchViewModel
) {
	val booksResults: List<Book> by searchViewModel.booksResults.observeAsState(listOf())
	val usersResults: List<User> by searchViewModel.usersResults.observeAsState(listOf())
	val searchQuery: String by searchViewModel.searchQuery.observeAsState("")
	val searchBooksOption: Boolean by searchViewModel.searchBooksOption.observeAsState(true)

	LazyColumn(
		contentPadding = paddingValues,
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(10.dp)
	) {
		item {
			TextField(
				modifier = Modifier.fillMaxWidth(),
				value = searchQuery,
				onValueChange = { searchViewModel.changeSearchQuery(it) },
				trailingIcon = {
					IconButton(onClick = {
						if (searchBooksOption) {
							searchViewModel.searchBooks(navController.context, searchQuery)
						} else {
							searchViewModel.searchUsers(navController.context, searchQuery)
						}
					}) {
						Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
					}
				}
			)
		}
		item {
			Row(
				verticalAlignment = Alignment.CenterVertically
			) {
				RadioButton(
					selected = searchBooksOption,
					onClick = {
						searchViewModel.changeSearchBooksOption(true)
					})
				Text(text = "Buscar libros")
			}
			Row(
				verticalAlignment = Alignment.CenterVertically
			) {
				RadioButton(
					selected = !searchBooksOption,
					onClick = {
						searchViewModel.changeSearchBooksOption(false)
					})
				Text(text = "Buscar usuarios")
			}
		}
		if (searchBooksOption) {
			items(booksResults) { book ->
				Text(text = book.name)
			}
		} else {
			items(usersResults) { user ->
				Text(text = user.username)
			}
		}
	}
}