package com.sebas.booksapp.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sebas.booksapp.views.BookDetailScreen
import com.sebas.booksapp.views.LoginScreen
import com.sebas.booksapp.views.PopularBooksScreen
import com.sebas.booksapp.views.ProfileScreen
import com.sebas.booksapp.views.ReadListScreen

@Composable
fun NavManager(drawerState: DrawerState, navController: NavHostController) {
	NavHost(navController = navController, startDestination = "loginScreen") {
		composable("loginScreen") {
			LoginScreen(navController)
		}
		composable("popularBooksScreen") {
			PopularBooksScreen(drawerState, navController)
		}
		composable("profileScreen") {
			ProfileScreen(drawerState, navController)
		}
		composable("bookDetailScreen/{bookId}") { backStackEntry ->
			val bookId = backStackEntry.arguments?.getString("bookId")
			BookDetailScreen(drawerState, navController, bookId!!)
		}
		composable("readListScreen") {
			ReadListScreen(drawerState, navController)
		}
	}
}