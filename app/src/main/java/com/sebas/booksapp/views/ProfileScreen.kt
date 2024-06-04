package com.sebas.booksapp.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sebas.booksapp.R
import com.sebas.booksapp.models.User
import com.sebas.booksapp.viewmodels.ProfileViewModel
import com.sebas.booksapp.views.components.ButtonItemProfile
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
				Text(
					text = user?.name ?: "Loading...",
					softWrap = true,
					style = MaterialTheme.typography.bodyLarge
				)
				Text(
					text = "@${user?.username}" ?: "Loading...",
					softWrap = true,
					style = MaterialTheme.typography.bodyMedium
				)
				if (user?.image_profile_path != null) {
					AsyncImage(
						model = user?.image_profile_path,
						contentDescription = "Profile image",
						modifier = Modifier
							.width(85.dp)
							.clip(CircleShape)
					)
				} else {
					Image(
						modifier = Modifier
							.width(85.dp)
							.clip(CircleShape),
						painter = painterResource(
							R.drawable.userprofiledefault,
						),
						contentDescription = "Default profile image"
					)
				}
				if (user?.isMe == false) {
					if (user?.isFollowing == true) {
						TextButton(onClick = {
							profileViewModel.unfollowUser(user.id, context)
						}) {
							Text("Siguiendo")
						}
					} else {
						TextButton(onClick = {
							profileViewModel.followUser(user.id, context)
						}) {
							Text("Seguir")
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
				verticalArrangement = Arrangement.spacedBy(8.dp),
			) {
				ButtonItemProfile(
					ruta = "readListScreen?userId=${user?.id}",
					navController = navController,
					textButton = "Libros leídos",
					iconButton = Icons.Default.Bookmark
				)
				ButtonItemProfile(
					ruta = "watchListScreen?userId=${user?.id}",
					navController = navController,
					textButton = "Libros por leer",
					iconButton = Icons.Default.WatchLater
				)
				ButtonItemProfile(
					ruta = "collectionListScreen?userId=${user?.id}",
					navController = navController,
					textButton = "Colección",
					iconButton = Icons.Default.CollectionsBookmark
				)
				ButtonItemProfile(
					ruta = "reviewsScreen?userId=${user?.id}",
					navController = navController,
					textButton = "Reseñas",
					iconButton = Icons.Default.Reviews
				)
				ButtonItemProfile(
					ruta = "followersScreen/${user?.id}",
					navController = navController,
					textButton = "Seguidores",
					iconButton = Icons.Default.Person
				)
				ButtonItemProfile(
					ruta = "followingScreen/${user?.id}",
					navController = navController,
					textButton = "Siguiendo",
					iconButton = Icons.Default.Person
				)
			}
		}
	}
}
