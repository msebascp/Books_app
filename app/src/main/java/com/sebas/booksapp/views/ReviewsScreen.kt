package com.sebas.booksapp.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sebas.booksapp.viewmodels.ReviewsViewModel
import com.sebas.booksapp.views.components.LoadingScreen
import com.sebas.booksapp.views.components.TopBar

@Composable
fun ReviewsScreen(
	drawerState: DrawerState,
	navController: NavController,
	reviewViewModel: ReviewsViewModel = viewModel(),
) {
	reviewViewModel.getReviews(LocalContext.current)
	val isLoading by reviewViewModel.isLoading.observeAsState(true)
	val books by reviewViewModel.reviews.observeAsState(emptyList())
	val scope = rememberCoroutineScope()

	Scaffold(
		topBar = {
			TopBar(navController = navController, drawerState, scope, title = "Libros por leer")
		},
		content = { paddingValues ->
			if (isLoading) {
				LoadingScreen()
			} else {
				ReviewsContent(paddingValues)
			}
		},
	)
}

@Composable
fun ReviewsContent(paddingValues: PaddingValues) {
	TODO("Not yet implemented")
}
