package com.sebas.booksapp.views

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sebas.booksapp.models.User
import com.sebas.booksapp.viewmodels.ProfileViewModel
import com.sebas.booksapp.views.components.TopBar

@Composable
fun ProfileScreen(
	drawerState: DrawerState,
	navController: NavController,
	userId: String? = "",
	profileViewModel: ProfileViewModel = viewModel()
) {
	val context = LocalContext.current
	LaunchedEffect(userId) {
		profileViewModel.getUser(context, userId)
	}
	val scope = rememberCoroutineScope()
	val user: User? by profileViewModel.user.observeAsState()
	Scaffold(
		topBar = {
			TopBar(navController = navController, drawerState, scope, true, "Perfil")
		},
		content = { paddingValues ->
			if (user != null) {
				ProfileContent(paddingValues, user, context, navController, profileViewModel)
			}
		},
	)
}

@Composable
fun ProfileContent(
	paddingValues: PaddingValues,
	user: User?,
	context: Context,
	navController: NavController,
	profileViewModel: ProfileViewModel
) {
	LazyColumn(
		contentPadding = paddingValues,
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
			.padding(top = 0.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		item {
			Column(
				modifier = Modifier
					.fillMaxWidth(),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Text(text = user?.name ?: "Loading...")
				AsyncImage(
					model = user?.image_profile_path,
					contentDescription = "Profile image",
					modifier = Modifier
						.width(85.dp)
						.clip(CircleShape)
				)
				if (user?.isMe == false) {
					if (user?.isFollowing == true) {
						TextButton(onClick = {
							profileViewModel.unfollowUser(user.id, context)
						}) {
							Text("Unfollow")
						}
					} else {
						TextButton(onClick = {
							profileViewModel.followUser(user.id, context)
						}) {
							Text("Follow")
						}
					}
				}
			}
		}
		item {
			Divider()
		}
		item {
			Column(
				modifier = Modifier
					.fillMaxWidth(),
				verticalArrangement = Arrangement.spacedBy(8.dp)
			) {
				TextButton(onClick = {
					navController.navigate("readListScreen?userId=${user?.id}")
				}) {
					Text("Libros leídos")
				}
				TextButton(onClick = {
					navController.navigate("collectionListScreen?userId=${user?.id}")
				}) {
					Text("Colección")
				}
				TextButton(onClick = {
					navController.navigate("watchListScreen?userId=${user?.id}")
				}) {
					Text("Libros por leer")
				}
				TextButton(onClick = {
					navController.navigate("reviewsScreen?userId=${user?.id}")
				}) {
					Text("Reseñas")
				}
				TextButton(onClick = {
					navController.navigate("followersScreen/${user?.id}")
				}) {
					Text("Seguidores")
				}
				TextButton(onClick = {
					navController.navigate("followingScreen/${user?.id}")
				}) {
					Text("Siguiendo")
				}
			}
		}
	}
}
