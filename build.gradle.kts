// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
	dependencies {
		//Hilt
		classpath("com.google.dagger:hilt-android-gradle-plugin:2.47")
	}
}

plugins {
	alias(libs.plugins.androidApplication) apply false
	alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}