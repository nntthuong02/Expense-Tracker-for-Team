package com.example.expensetrackerforteam.presentation.welcome_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.expensetrackerforteam.domain.model.CurrencyModel

@Composable
fun CurrencyScreen(
    navController: NavController,
    setting: Boolean?,
) {
    Text("CurrencyScreen")
}