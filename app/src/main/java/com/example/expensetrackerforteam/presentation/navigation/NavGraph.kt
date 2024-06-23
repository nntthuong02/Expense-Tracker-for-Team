package com.example.expensetrackerforteam.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensetrackerforteam.presentation.home_screen.HomeScreen
import com.example.expensetrackerforteam.presentation.onboarding.OnboardingScreen
import com.example.expensetrackerforteam.presentation.onboarding.OnboardingViewModel
import com.example.expensetrackerforteam.presentation.welcome_screen.CurrencyScreen


@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalUnitApi
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Route.OnboardingScreen.route) {
            val viewModel: OnboardingViewModel = hiltViewModel()
            OnboardingScreen(navController)
        }
//        composable(route = Route.WelcomeScreen.route) {
//            Text("WelcomeScreen")
//        }
        composable(route = "${Route.CurrencyScreen.route}") {
            CurrencyScreen(navController = rememberNavController())
        }
        composable(route = Route.HomeScreen.route) {
            HomeScreen(navController = rememberNavController())
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