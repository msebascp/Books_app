package com.sebas.booksapp.views

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
import com.sebas.booksapp.models.User
import com.sebas.booksapp.viewmodels.FollowingViewModel
import com.sebas.booksapp.views.components.CardUser
import com.sebas.booksapp.views.components.TopBar

@Composable
fun FollowingScreen(
	drawerState: DrawerState,
	navController: NavController,
	userId: String,
	followingViewModel: FollowingViewModel = viewModel(),
) {
	followingViewModel.getFollowing(LocalContext.current, userId)
	val scope = rememberCoroutineScope()
	val users: List<User> by followingViewModel.following.observeAsState(emptyList())

	Scaffold(
		topBar = {
			TopBar(navController = navController, drawerState, scope, true, "Siguiendo")
		},
		content = { paddingValues ->
			FollowingContent(paddingValues, navController, followingViewModel, users)
		}
	)
}

@Composable
fun FollowingContent(
	paddingValues: PaddingValues,
	navController: NavController,
	followingViewModel: FollowingViewModel,
	users: List<User>
) {
	LazyColumn(
		modifier = Modifier
			.fillMaxWidth()
			.padding(paddingValues),
		contentPadding = PaddingValues(16.dp)
	) {
		items(users) { user ->
			CardUser(navController = navController, user = user)
			Divider(modifier = Modifier.padding(bottom = 8.dp))
		}
	}
}