package com.sebas.booksapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

private val darkScheme = darkColorScheme(
	primary = primaryDark,
	onPrimary = onPrimaryDark,
	primaryContainer = primaryContainerDark,
	onPrimaryContainer = onPrimaryContainerDark,
	secondary = secondaryDark,
	onSecondary = onSecondaryDark,
	secondaryContainer = secondaryContainerDark,
	onSecondaryContainer = onSecondaryContainerDark,
	tertiary = tertiaryDark,
	onTertiary = onTertiaryDark,
	tertiaryContainer = tertiaryContainerDark,
	onTertiaryContainer = onTertiaryContainerDark,
	error = errorDark,
	onError = onErrorDark,
	errorContainer = errorContainerDark,
	onErrorContainer = onErrorContainerDark,
	background = backgroundDark,
	onBackground = onBackgroundDark,
	surface = surfaceDark,
	onSurface = onSurfaceDark,
	surfaceVariant = surfaceVariantDark,
	onSurfaceVariant = onSurfaceVariantDark,
	outline = outlineDark,
	outlineVariant = outlineVariantDark,
	scrim = scrimDark,
	inverseSurface = inverseSurfaceDark,
	inverseOnSurface = inverseOnSurfaceDark,
	inversePrimary = inversePrimaryDark,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
	primary = primaryDarkMediumContrast,
	onPrimary = onPrimaryDarkMediumContrast,
	primaryContainer = primaryContainerDarkMediumContrast,
	onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
	secondary = secondaryDarkMediumContrast,
	onSecondary = onSecondaryDarkMediumContrast,
	secondaryContainer = secondaryContainerDarkMediumContrast,
	onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
	tertiary = tertiaryDarkMediumContrast,
	onTertiary = onTertiaryDarkMediumContrast,
	tertiaryContainer = tertiaryContainerDarkMediumContrast,
	onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
	error = errorDarkMediumContrast,
	onError = onErrorDarkMediumContrast,
	errorContainer = errorContainerDarkMediumContrast,
	onErrorContainer = onErrorContainerDarkMediumContrast,
	background = backgroundDarkMediumContrast,
	onBackground = onBackgroundDarkMediumContrast,
	surface = surfaceDarkMediumContrast,
	onSurface = onSurfaceDarkMediumContrast,
	surfaceVariant = surfaceVariantDarkMediumContrast,
	onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
	outline = outlineDarkMediumContrast,
	outlineVariant = outlineVariantDarkMediumContrast,
	scrim = scrimDarkMediumContrast,
	inverseSurface = inverseSurfaceDarkMediumContrast,
	inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
	inversePrimary = inversePrimaryDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
	primary = primaryDarkHighContrast,
	onPrimary = onPrimaryDarkHighContrast,
	primaryContainer = primaryContainerDarkHighContrast,
	onPrimaryContainer = onPrimaryContainerDarkHighContrast,
	secondary = secondaryDarkHighContrast,
	onSecondary = onSecondaryDarkHighContrast,
	secondaryContainer = secondaryContainerDarkHighContrast,
	onSecondaryContainer = onSecondaryContainerDarkHighContrast,
	tertiary = tertiaryDarkHighContrast,
	onTertiary = onTertiaryDarkHighContrast,
	tertiaryContainer = tertiaryContainerDarkHighContrast,
	onTertiaryContainer = onTertiaryContainerDarkHighContrast,
	error = errorDarkHighContrast,
	onError = onErrorDarkHighContrast,
	errorContainer = errorContainerDarkHighContrast,
	onErrorContainer = onErrorContainerDarkHighContrast,
	background = backgroundDarkHighContrast,
	onBackground = onBackgroundDarkHighContrast,
	surface = surfaceDarkHighContrast,
	onSurface = onSurfaceDarkHighContrast,
	surfaceVariant = surfaceVariantDarkHighContrast,
	onSurfaceVariant = onSurfaceVariantDarkHighContrast,
	outline = outlineDarkHighContrast,
	outlineVariant = outlineVariantDarkHighContrast,
	scrim = scrimDarkHighContrast,
	inverseSurface = inverseSurfaceDarkHighContrast,
	inverseOnSurface = inverseOnSurfaceDarkHighContrast,
	inversePrimary = inversePrimaryDarkHighContrast
)

@Immutable
data class ColorFamily(
	val color: Color,
	val onColor: Color,
	val colorContainer: Color,
	val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
	Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun BooksAppTheme(
	useDarkTheme: Boolean = isSystemInDarkTheme(),
	dynamicColor: Boolean = true,
	content: @Composable() () -> Unit
) {
	val colors = mediumContrastDarkColorScheme

	MaterialTheme(
		colorScheme = colors,
		typography = AppTypography,
		content = content
	)
}

