package com.sebas.booksapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sebas.booksapp.viewmodels.AddReviewViewModel
import com.sebas.booksapp.views.components.TopBar

@Composable
fun AddReviewScreen(
	drawerState: DrawerState,
	navController: NavHostController,
	bookId: String,
	addReviewViewModel: AddReviewViewModel = viewModel()
) {
	val scope = rememberCoroutineScope()
	Scaffold(
		topBar = {
			TopBar(navController = navController, drawerState, scope, true, "Añadir reseña")
		},
		content = { paddingValues ->
			AddReviewContent(paddingValues, bookId, addReviewViewModel, navController)
		}
	)
}

@Composable
fun AddReviewContent(
	paddingValues: PaddingValues,
	bookId: String,
	addReviewViewModel: AddReviewViewModel,
	navController: NavHostController
) {
	val context = LocalContext.current
	val review by addReviewViewModel.review.observeAsState("")
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(paddingValues)
			.padding(16.dp)
			.verticalScroll(rememberScrollState())
			.statusBarsPadding()
			.safeDrawingPadding(),
	) {
		TextField(
			value = review,
			onValueChange = { addReviewViewModel.changeReview(it) },
			modifier = Modifier.fillMaxWidth(),
			minLines = 15,
		)
		FilledTonalButton(
			onClick = {
				addReviewViewModel.addReview(bookId, context)
				navController.popBackStack()
			},
			modifier = Modifier
				.padding(top = 16.dp)
				.fillMaxWidth()
		) {
			Text("Añadir reseña")
		}
	}
}
