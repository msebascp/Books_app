package com.sebas.booksapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
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
import com.sebas.booksapp.models.Review
import com.sebas.booksapp.viewmodels.ReviewsViewModel
import com.sebas.booksapp.views.components.CardReviewUser
import com.sebas.booksapp.views.components.LoadingScreen
import com.sebas.booksapp.views.components.TopBar

@Composable
fun ReviewsScreen(
	drawerState: DrawerState,
	navController: NavController,
	userId: String?,
	reviewsViewModel: ReviewsViewModel = viewModel(),
) {
	reviewsViewModel.getReviews(LocalContext.current, userId)
	val isLoading by reviewsViewModel.isLoading.observeAsState(true)
	val reviews by reviewsViewModel.reviews.observeAsState(emptyList())
	val scope = rememberCoroutineScope()

	Scaffold(
		topBar = {
			TopBar(
				navController = navController,
				drawerState,
				scope,
				title = "Reseñas",
				goBack = true
			)
		},
		content = { paddingValues ->
			if (isLoading) {
				LoadingScreen()
			} else {
				ReviewsContent(paddingValues, navController, reviews)
			}
		},
	)
}

@Composable
fun ReviewsContent(
	paddingValues: PaddingValues,
	navController: NavController,
	reviews: List<Review>
) {
	LazyColumn(
		modifier = Modifier
			.padding(paddingValues)
			.fillMaxWidth(),
		contentPadding = PaddingValues(16.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp),
	) {
		items(reviews) { review ->
			CardReviewUser(navController, review)
			Divider()
		}
	}
}
