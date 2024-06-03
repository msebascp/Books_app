package com.sebas.booksapp.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sebas.booksapp.views.AddCommentScreen
import com.sebas.booksapp.views.AddReviewScreen
import com.sebas.booksapp.views.BookDetailScreen
import com.sebas.booksapp.views.CollectionListScreen
import com.sebas.booksapp.views.FollowersScreen
import com.sebas.booksapp.views.FollowingScreen
import com.sebas.booksapp.views.ForgotPasswordScreen
import com.sebas.booksapp.views.LoginScreen
import com.sebas.booksapp.views.PopularBooksScreen
import com.sebas.booksapp.views.ProfileScreen
import com.sebas.booksapp.views.ReadBookDetailScreen
import com.sebas.booksapp.views.ReadListScreen
import com.sebas.booksapp.views.RegisterScreen
import com.sebas.booksapp.views.ReviewsScreen
import com.sebas.booksapp.views.SearchScreen
import com.sebas.booksapp.views.WatchListScreen

@Composable
fun NavManager(drawerState: DrawerState, navController: NavHostController) {
	NavHost(navController = navController, startDestination = "loginScreen") {
		composable("addCommentScreen/{reviewId}") { backStackEntry ->
			val reviewId = backStackEntry.arguments?.getString("reviewId")
			AddCommentScreen(drawerState, navController, reviewId!!)
		}
		composable("addReviewScreen/{bookId}") { backStackEntry ->
			val bookId = backStackEntry.arguments?.getString("bookId")
			AddReviewScreen(drawerState, navController, bookId!!)
		}
		composable("bookDetailScreen/{bookId}") { backStackEntry ->
			val bookId = backStackEntry.arguments?.getString("bookId")
			BookDetailScreen(drawerState, navController, bookId!!)
		}
		composable(
			"collectionListScreen?userId={userId}",
			arguments = listOf(navArgument("userId") { defaultValue = "" })
		) { backStackEntry ->
			val userId = backStackEntry.arguments?.getString("userId")
			CollectionListScreen(drawerState, navController, userId)
		}
		composable("followersScreen/{userId}") { backStackEntry ->
			val userId = backStackEntry.arguments?.getString("userId")
			FollowersScreen(drawerState, navController, userId!!)
		}
		composable("followingScreen/{userId}") { backStackEntry ->
			val userId = backStackEntry.arguments?.getString("userId")
			FollowingScreen(drawerState, navController, userId!!)
		}
		composable("forgotPasswordScreen") {
			ForgotPasswordScreen(navController)
		}
		composable("loginScreen") {
			LoginScreen(navController)
		}
		composable("popularBooksScreen") {
			PopularBooksScreen(drawerState, navController)
		}
		composable(
			"profileScreen?userId={userId}",
			arguments = listOf(navArgument("userId") { defaultValue = "" })
		) { backStackEntry ->
			val userId = backStackEntry.arguments?.getString("userId")
			ProfileScreen(drawerState, navController, userId)
		}
		composable(
			"readBookDetailScreen/{bookId}?userId={userId}",
			arguments = listOf(navArgument("userId") { defaultValue = "" })
		) { backStackEntry ->
			val bookId = backStackEntry.arguments?.getString("bookId")
			val userId = backStackEntry.arguments?.getString("userId")
			ReadBookDetailScreen(drawerState, navController, bookId!!, userId)
		}
		composable(
			"readListScreen?userId={userId}",
			arguments = listOf(navArgument("userId") { defaultValue = "" })
		) { backStackEntry ->
			val userId = backStackEntry.arguments?.getString("userId")
			ReadListScreen(drawerState, navController, userId)
		}
		composable("registerScreen") {
			RegisterScreen(navController)
		}
		composable(
			"reviewsScreen?userId={userId}",
			arguments = listOf(navArgument("userId") { defaultValue = "" })
		) { backStackEntry ->
			val userId = backStackEntry.arguments?.getString("userId")
			ReviewsScreen(drawerState, navController, userId)
		}
		composable("searchScreen") {
			SearchScreen(drawerState, navController)
		}
		composable(
			"watchListScreen?userId={userId}",
			arguments = listOf(navArgument("userId") { defaultValue = "" })
		) { backStackEntry ->
			val userId = backStackEntry.arguments?.getString("userId")
			WatchListScreen(drawerState, navController, userId)
		}

	}
}