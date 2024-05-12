package com.sebas.booksapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebas.booksapp.views.LoginScreen
import com.sebas.booksapp.views.PopularBooksScreen

@Composable
fun NavManager() {
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = "loginScreen") {
		composable("loginScreen") {
			LoginScreen(navController)
		}
		composable("popularBooksScreen") {
			PopularBooksScreen(navController)
		}
	}
}