package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.room.RoomDatabase

@Composable
fun Navigation(navController: NavHostController, database: AppDatabase) {

    val sharedPreferences =
        LocalContext.current.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    val isLoggedIn = sharedPreferences.getBoolean("loggedIn", false)

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) {
            Home.route
        } else {
            OnBoarding.route
        }
    ) {
        composable(OnBoarding.route) {
            OnBoarding(navController)
        }
        composable(Home.route) {
            Home(navController, database = database)
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}
