package com.example.expensetrackerforteam.presentation.navigation.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensetrackerforteam.ui.theme.ExpenseTrackerForTeamTheme


@ExperimentalAnimationApi
@Composable
fun BottomNavBar(
    items: List<NavBarItemHolder>,
    navController: NavController,
    itemClick: (NavBarItemHolder) -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    Card(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry?.destination?.route
                NavBarItem(
                    item = item,
                    //Xu li trong MainScreen
                    onClick = { itemClick(item) },
                    selected = selected
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    val items = listOf(
        NavBarItemHolder("Home", Icons.Filled.Home, "home"),
        NavBarItemHolder("Insight", Icons.Filled.Info, "insight"),
        NavBarItemHolder("Participant", Icons.Filled.Person, "participant"),
        NavBarItemHolder("Settings", Icons.Filled.Settings, "settings")
    )

    val navController = rememberNavController()

    ExpenseTrackerForTeamTheme {
            BottomNavBar(
                items = items,
                navController = navController,
                itemClick = { /* Handle item click here */ }
            )
    }
}