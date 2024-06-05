package com.example.expensetrackerforteam.presentation.navigation.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.expensetrackerforteam.presentation.navigation.Route
import com.example.expensetrackerforteam.ui.theme.ExpenseTrackerForTeamTheme

data class NavBarItemHolder(
    val title: String,
    val icon: ImageVector,
    val route: String
)

fun provideBottomNavItems() = listOf(
    NavBarItemHolder(
        "",
        Icons.Filled.Home,
        Route.HomeScreen.route
    ),
    NavBarItemHolder(
        "",
        Icons.Filled.CheckCircle,
        Route.InsightScreen.route
    ),
    NavBarItemHolder(
        "",
        Icons.Filled.Person,
        Route.ParticipantScreen.route
    ),
    NavBarItemHolder(
        "",
        Icons.Filled.Settings,
        Route.SettingScreen.route
    )
)