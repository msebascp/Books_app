package com.sebas.booksapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
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
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sebas.booksapp.navigation.NavManager
import com.sebas.booksapp.ui.theme.BooksAppTheme
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
					color = MaterialTheme.colorScheme.background
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
		Text("Drawer title", modifier = Modifier.padding(16.dp))
		Divider()
		NavigationDrawerItem(
			label = { Text(text = "Popular") },
			selected = false,
			onClick = {
				navController.navigate("popularBooksScreen")
				scope.launch { drawerState.close() }
			}
		)
		NavigationDrawerItem(
			label = { Text(text = "Perfil") },
			selected = false,
			onClick = {
				navController.navigate("profileScreen")
				scope.launch { drawerState.close() }
			}
		)
		NavigationDrawerItem(
			label = { Text(text = "Leídos") },
			selected = false,
			onClick = {
				navController.navigate("readListScreen")
				scope.launch { drawerState.close() }
			}
		)
		NavigationDrawerItem(
			label = { Text(text = "Por leer") },
			selected = false,
			onClick = { /*TODO*/ }
		)
		NavigationDrawerItem(
			label = { Text(text = "Colección") },
			selected = false,
			onClick = { /*TODO*/ }
		)
		NavigationDrawerItem(
			label = { Text(text = "Reseñas") },
			selected = false,
			onClick = { /*TODO*/ }
		)
		NavigationDrawerItem(
			label = { Text(text = "Configuración") },
			selected = false,
			onClick = { /*TODO*/ }
		)
		NavigationDrawerItem(
			label = { Text(text = "Cerrar sesión") },
			selected = false,
			onClick = { /*TODO*/ }
		)
	}
}