package com.sebas.booksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sebas.booksapp.navigation.NavManager
import com.sebas.booksapp.ui.theme.BooksAppTheme
import com.sebas.booksapp.viewmodels.LoginViewModel
import com.sebas.booksapp.views.LoginScreen

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			BooksAppTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					NavManager(LoginViewModel())
				}
			}
		}
	}
}