package com.example.expensetrackerforteam.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.expensetrackerforteam.presentation.home_screen.HomeScreen
import com.example.expensetrackerforteam.presentation.welcome_screen.CurrencyScreen
import com.example.expensetrackerforteam.presentation.welcome_screen.WelcomeScreen

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalUnitApi
@Composable
fun MainNavigation(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Route.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = "${Route.CurrencyScreen.route}/{setting}") {
            Text("CurrencyScreen")
        }
        composable(route = Route.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Route.TransactionScreen.route) {
            Text("TransactionScreen")
        }
        composable(route = Route.InsightScreen.route) {
            Text("InsightScreen")
        }
        composable(route = Route.ParticipantScreen.route) {
            Text("ParticipantScreen")
        }
        composable(
            route = "${Route.ParticipantDetailScreen.route}/{participantName}",
        ) {
            Text("ParticipantDetailScreen")
        }
        composable(route = Route.SettingScreen.route) {
            Text("SettingScreen")
        }
    }
}