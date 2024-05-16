package com.sebas.booksapp.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sebas.booksapp.views.components.TopBar

@Composable
fun ProfileScreen(
	drawerState: DrawerState,
	navController: NavController,
) {
	val scope = rememberCoroutineScope()
	Scaffold(
		topBar = {
			TopBar(navController = navController, drawerState, scope)
		},
		content = { paddingValues ->
			Text(text = "Profile Screen", modifier = Modifier.padding(paddingValues))
		},
	)
}