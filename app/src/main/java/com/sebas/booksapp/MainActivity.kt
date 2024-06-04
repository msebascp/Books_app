package com.sebas.booksapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BlurOn
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sebas.booksapp.navigation.NavManager
import com.sebas.booksapp.network.ApiRepository
import com.sebas.booksapp.ui.theme.BooksAppTheme
import com.sebas.booksapp.views.components.IconApp
import kotlinx.coroutines.launch


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "AuthUser")

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		enableEdgeToEdge()
		super.onCreate(savedInstanceState)
		setContent {
			BooksAppTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.surface
				) {
					val navController = rememberNavController()
					val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
					ModalNavigationDrawer(
						drawerState = drawerState,
						drawerContent = {
							Menu(drawerState, navController)
						},
					) {
						NavManager(drawerState, navController)
					}
				}
			}
		}
	}
}

@Composable
fun Menu(drawerState: DrawerState, navController: NavHostController) {
	val scope = rememberCoroutineScope()
	ModalDrawerSheet {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 16.dp),
			horizontalArrangement = Arrangement.Center
		) {
			IconApp(width = 75)
		}
		NavigationDrawerItem(
			icon = {
				Icon(
					imageVector = Icons.Default.AccountCircle,
					contentDescription = "Perfil"
				)
			},
			label = { Text(text = "Perfil") },
			selected = false,
			onClick = {
				navController.navigate("profileScreen")
				scope.launch { drawerState.close() }
			}
		)
		NavigationDrawerItem(
			icon = {
				Icon(
					imageVector = Icons.Default.BlurOn,
					contentDescription = "Buscar"
				)
			},
			label = { Text(text = "Popular") },
			selected = false,
			onClick = {
				navController.navigate("popularBooksScreen")
				scope.launch { drawerState.close() }
			}
		)
		NavigationDrawerItem(
			icon = {
				Icon(
					imageVector = Icons.Default.Search,
					contentDescription = "Buscar"
				)
			},
			label = { Text(text = "Buscar") },
			selected = false,
			onClick = {
				navController.navigate("searchScreen")
				scope.launch { drawerState.close() }
			}
		)
		NavigationDrawerItem(
			icon = {
				Icon(
					imageVector = Icons.Default.BookmarkAdd,
					contentDescription = "Leídos"
				)
			},
			label = { Text(text = "Leídos") },
			selected = false,
			onClick = {
				navController.navigate("readListScreen")
				scope.launch { drawerState.close() }
			}
		)
		NavigationDrawerItem(
			icon = {
				Icon(
					imageVector = Icons.Default.WatchLater,
					contentDescription = "Leídos"
				)
			},
			label = { Text(text = "Por leer") },
			selected = false,
			onClick = {
				navController.navigate("watchListScreen")
				scope.launch { drawerState.close() }
			}
		)
		NavigationDrawerItem(
			icon = {
				Icon(
					imageVector = Icons.Default.CollectionsBookmark,
					contentDescription = "Leídos"
				)
			},
			label = { Text(text = "Colección") },
			selected = false,
			onClick = {
				navController.navigate("collectionListScreen")
				scope.launch { drawerState.close() }
			}
		)
		NavigationDrawerItem(
			icon = {
				Icon(
					imageVector = Icons.Default.Reviews,
					contentDescription = "Leídos"
				)
			},
			label = { Text(text = "Reseñas") },
			selected = false,
			onClick = {
				navController.navigate("reviewsScreen")
				scope.launch { drawerState.close() }
			}
		)
		val context = LocalContext.current
		NavigationDrawerItem(
			icon = {
				Icon(
					imageVector = Icons.AutoMirrored.Default.Logout,
					contentDescription = "Leídos"
				)
			},
			label = { Text(text = "Cerrar sesión") },
			selected = false,
			onClick = {
				scope.launch {
					try {
						val repository = ApiRepository()
						val token = AuthStore.getToken(context)
						repository.logout(token)
						Log.d("MainActivity", "Logout finished")
					} catch (e: Exception) {
						Log.e("MainActivity", "Logout failed with exception: $e")
					} finally {
						navController.navigate("loginScreen")
						drawerState.close()
					}
				}
			}
		)
	}
}