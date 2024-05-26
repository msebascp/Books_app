package com.sebas.booksapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.sebas.booksapp.viewmodels.AddCommentViewModel
import com.sebas.booksapp.views.components.TopBar

@Composable
fun AddCommentScreen(
	drawerState: DrawerState,
	navController: NavHostController,
	reviewId: String,
	addCommentViewModel: AddCommentViewModel = viewModel()
) {
	val scope = rememberCoroutineScope()
	Scaffold(
		topBar = {
			TopBar(navController = navController, drawerState, scope, true, "Añadir comentario")
		},
		content = { paddingValues ->
			AddCommentContent(paddingValues, reviewId, addCommentViewModel, navController)
		}
	)
}

@Composable
fun AddCommentContent(
	paddingValues: PaddingValues,
	reviewId: String,
	addCommentViewModel: AddCommentViewModel,
	navController: NavHostController
) {
	val context = LocalContext.current
	val comment by addCommentViewModel.comment.observeAsState("")
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(paddingValues)
			.padding(16.dp)
	) {
		TextField(
			value = comment,
			onValueChange = { addCommentViewModel.changeComment(it) },
			modifier = Modifier.fillMaxWidth(),
			minLines = 15,
		)
		FilledTonalButton(
			onClick = {
				addCommentViewModel.addComment(reviewId, context)
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