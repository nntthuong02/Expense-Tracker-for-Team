package com.example.expensetrackerforteam.presentation.navigation.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.expensetrackerforteam.presentation.navigation.Route

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
        Icons.Filled.Info,
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