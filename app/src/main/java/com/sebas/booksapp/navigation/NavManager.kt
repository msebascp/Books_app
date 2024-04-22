package com.sebas.booksapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebas.booksapp.viewmodels.LoginViewModel
import com.sebas.booksapp.views.LoginScreen

@Composable
fun NavManager(loginViewModel: LoginViewModel) {
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = "loginScreen") {
		composable("loginScreen") {
			LoginScreen(viewModel = loginViewModel)
		}
	}
}